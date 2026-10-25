import config.SimulationConfig;
import model.Island;
import model.animal.Animal;
import service.SimulationService;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println("=== Остров ОСТАТЬСЯ В ЖИВЫХ ===\n");

        // Создаём остров
        Island island = new Island(SimulationConfig.ISLAND_X, SimulationConfig.ISLAND_Y);
        System.out.println("Создали остров: " + island);

        // Генерируем растения

        island.generatePlants(
            SimulationConfig.PLANTS_MIN_PER_CELL,
            SimulationConfig.PLANTS_MAX_PER_CELL
        );
        System.out.println("Засеяли ганджубас");

        // Создаём начальных животных
        List<Animal> initialAnimals = SimulationConfig.createInitialAnimals();
        System.out.println("Создано " + initialAnimals.size() + " животных");

        // Размещаем животных на острове
        island.populateAnimals(initialAnimals);
        System.out.println("Заселили животных\n");

        //
        // Запускаем симуляцию
        SimulationService simulation = new SimulationService(island);
        simulation.start();
    }
}
