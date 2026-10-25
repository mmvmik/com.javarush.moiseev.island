package service;

import config.SimulationConfig;
import model.Island;
import model.Location;
import model.Plant;
import model.animal.Animal;
import stats.StatisticsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationService {

    private final Island island;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService animalExecutor;
    private volatile boolean running = true;
    private int tick = 0;

    public SimulationService(Island island) {
        this.island = island;
        this.scheduler = Executors.newScheduledThreadPool(3);
        this.animalExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

   // Запускаем симуляцию
    public void start() {
        System.out.println("Размер мира симуляции " + island);

        // Запуск периодических задач
        scheduler.scheduleAtFixedRate(
            this::growPlants,
            0, SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS
        );

        scheduler.scheduleAtFixedRate(
            this::processAnimals,
            0, SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS
        );

        scheduler.scheduleAtFixedRate(
            () -> StatisticsPrinter.printStatistics(island, tick),
            0, SimulationConfig.TICK_DURATION_MS, TimeUnit.MILLISECONDS
        );

        System.out.println("Запускаем симуляцию. Длительность дня(тика): " + SimulationConfig.TICK_DURATION_MS + "ms");
        System.out.println("Нажмите  Enter для остановки симуляции...\n");

        // Ожидание ввода для остановки
        new Thread(() -> {
            try {
                System.in.read();
                stop();
            } catch (Exception e) {
                stop();
            }
        }).start();
    }

   // Растим растения
    private void growPlants() {
        List<Runnable> tasks = new ArrayList<>();
        for (int r = 0; r < island.getRows(); r++) {
            for (int c = 0; c < island.getCols(); c++) {
                final Location loc = island.getLocation(r, c);
                tasks.add(() -> {
                    for (Plant plant : loc.getPlants()) {
                        plant.grow(SimulationConfig.PLANT_GROWTH_RATE);
                    }
                    loc.removeDeadPlants();
                });
            }
        }
        executeTasks(tasks);
    }


//     Жизненный цикл животных

    private void processAnimals() {
        tick++;
        List<Runnable> tasks = new ArrayList<>();

        // Собираем всех живых животных
        List<Animal> allAnimals = island.getAllAnimals();

        for (Animal animal : allAnimals) {
            final Animal a = animal;
            tasks.add(() -> processSingleAnimal(a));
        }

        executeTasks(tasks);

        // Очистка мёртвых животных во всех локациях
        for (int r = 0; r < island.getRows(); r++) {
            for (int c = 0; c < island.getCols(); c++) {
                island.getLocation(r, c).removeDeadAnimals();
            }
        }

        // Проверка условия остановки
        if (SimulationConfig.STOP_ON_ALL_DEAD && allAnimals.isEmpty()) {
            System.out.println("Все умерли! Останавливаем симуляцию...");
            stop();
        }
    }


//     Обработка одного животного: еда, размножение, движение, голод.

    private void processSingleAnimal(Animal animal) {
        if (!animal.isAlive()) return;

        animal.eat();
        Animal offspring = animal.tryReproduce();
        if (offspring != null) {
            Location loc = animal.getLocation();
            if (loc != null) {
                loc.addAnimal(offspring);
            }
        }

        int[] direction = animal.chooseMoveDirection(island.getRows(), island.getCols());
        if (direction != null) {
            Location newLoc = island.getLocation(direction[0], direction[1]);
            Location oldLoc = animal.getLocation();
            if (newLoc != null && newLoc.addAnimal(animal)) {
            } else {
            }
        }

        animal.increaseHunger();
    }


    private void executeTasks(List<Runnable> tasks) {
        List<Future<?>> futures = new ArrayList<>();
        for (Runnable task : tasks) {
            futures.add(animalExecutor.submit(task));
        }
        // Ждём завершения всех задач (с таймаутом)
        for (Future<?> future : futures) {
            try {
                future.get(500, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                // Пропускаем timeout — следующий тик начнётся
            } catch (Exception e) {
                // Ignoring exceptions for individual tasks
            }
        }
    }

    public void stop() {
        if (!running) return;
        running = false;
        scheduler.shutdown();
        animalExecutor.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            if (!animalExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                animalExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            animalExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Симуляция остановлена после " + tick + " дней.");
    }

    public int getTick() {
        return tick;
    }
}
