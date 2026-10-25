package config;

import model.animal.Animal;
import model.animal.predator.*;
import model.animal.herbivore.*;

import java.util.ArrayList;
import java.util.List;


public class SimulationConfig {

    // Размеры острова
    public static final int ISLAND_X = 20;
    public static final int ISLAND_Y = 15;

    // Длительность такта (мс)
    public static final int TICK_DURATION_MS = 10000;

    // Растения
    public static final int PLANTS_MIN_PER_CELL = 2;
    public static final int PLANTS_MAX_PER_CELL = 8;
    public static final double PLANT_GROWTH_RATE = 0.3; // кг за такт

    // Условие остановки: умерли ли все животные
    public static boolean STOP_ON_ALL_DEAD = true;

    // Начальное количество животных каждого вида
    public static final int WOLF_COUNT = 10;
    public static final int BOA_COUNT = 8;
    public static final int FOX_COUNT = 15;
    public static final int BEAR_COUNT = 5;
    public static final int EAGLE_COUNT = 10;

    public static final int HORSE_COUNT = 15;
    public static final int DEER_COUNT = 20;
    public static final int RABBIT_COUNT = 40;
    public static final int MOUSE_COUNT = 50;
    public static final int GOAT_COUNT = 15;
    public static final int SHEEP_COUNT = 20;
    public static final int BOAR_COUNT = 15;
    public static final int BUFFALO_COUNT = 10;
    public static final int DUCK_COUNT = 25;
    public static final int CATERPILLAR_COUNT = 60;


    public static List<Animal> createInitialAnimals() {
        List<Animal> animals = new ArrayList<>();

        // Хищники
        for (int i = 0; i < WOLF_COUNT; i++) {
            Wolf wolf = new Wolf();
            wolf.setSpeciesId(animals.size());
            animals.add(wolf);
        }
        for (int i = 0; i < BOA_COUNT; i++) {
            BoaConstrictor boa = new BoaConstrictor();
            boa.setSpeciesId(animals.size());
            animals.add(boa);
        }
        for (int i = 0; i < FOX_COUNT; i++) {
            Fox fox = new Fox();
            fox.setSpeciesId(animals.size());
            animals.add(fox);
        }
        for (int i = 0; i < BEAR_COUNT; i++) {
            Bear bear = new Bear();
            bear.setSpeciesId(animals.size());
            animals.add(bear);
        }
        for (int i = 0; i < EAGLE_COUNT; i++) {
            Eagle eagle = new Eagle();
            eagle.setSpeciesId(animals.size());
            animals.add(eagle);
        }

        // Травоядные
        for (int i = 0; i < HORSE_COUNT; i++) {
            Horse horse = new Horse();
            horse.setSpeciesId(animals.size());
            animals.add(horse);
        }
        for (int i = 0; i < DEER_COUNT; i++) {
            Deer deer = new Deer();
            deer.setSpeciesId(animals.size());
            animals.add(deer);
        }
        for (int i = 0; i < RABBIT_COUNT; i++) {
            Rabbit rabbit = new Rabbit();
            rabbit.setSpeciesId(animals.size());
            animals.add(rabbit);
        }
        for (int i = 0; i < MOUSE_COUNT; i++) {
            Mouse mouse = new Mouse();
            mouse.setSpeciesId(animals.size());
            animals.add(mouse);
        }
        for (int i = 0; i < GOAT_COUNT; i++) {
            Goat goat = new Goat();
            goat.setSpeciesId(animals.size());
            animals.add(goat);
        }
        for (int i = 0; i < SHEEP_COUNT; i++) {
            Sheep sheep = new Sheep();
            sheep.setSpeciesId(animals.size());
            animals.add(sheep);
        }
        for (int i = 0; i < BOAR_COUNT; i++) {
            Boar boar = new Boar();
            boar.setSpeciesId(animals.size());
            animals.add(boar);
        }
        for (int i = 0; i < BUFFALO_COUNT; i++) {
            Buffalo buffalo = new Buffalo();
            buffalo.setSpeciesId(animals.size());
            animals.add(buffalo);
        }
        for (int i = 0; i < DUCK_COUNT; i++) {
            Duck duck = new Duck();
            duck.setSpeciesId(animals.size());
            animals.add(duck);
        }
        for (int i = 0; i < CATERPILLAR_COUNT; i++) {
            Caterpillar caterpillar = new Caterpillar();
            caterpillar.setSpeciesId(animals.size());
            animals.add(caterpillar);
        }

        return animals;
    }
}
