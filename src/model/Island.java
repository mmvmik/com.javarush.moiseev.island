package model;

import model.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Island {

    private final int rows;
    private final int cols;
    private final Location[][] locations;

    public Island(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.locations = new Location[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                locations[r][c] = new Location(r, c);
            }
        }
    }

//Размещаем животных
    public void populateAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            int row = ThreadLocalRandom.current().nextInt(rows);
            int col = ThreadLocalRandom.current().nextInt(cols);
            Location loc = locations[row][col];


            boolean added = loc.addAnimal(animal);
            if (!added) {
                // Ищем свободную клетку
                for (int attempt = 0; attempt < 100; attempt++) {
                    row = ThreadLocalRandom.current().nextInt(rows);
                    col = ThreadLocalRandom.current().nextInt(cols);
                    loc = locations[row][col];
                    if (loc.addAnimal(animal)) {
                        added = true;
                        break;
                    }
                }
            }
            if (!added) {
                System.err.println("Не могу разместить животных: " + animal.getName());
            }
        }
    }

// Засеиваем растения
    public void generatePlants(int minPerCell, int maxPerCell) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int count = ThreadLocalRandom.current().nextInt(minPerCell, maxPerCell + 1);
                for (int i = 0; i < count; i++) {
                    Plant plant = new Plant(ThreadLocalRandom.current().nextDouble(0.5, 2.0));
                    locations[r][c].addPlant(plant);
                }
            }
        }
    }

    public Location getLocation(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }
        return locations[row][col];
    }

    public List<Location> getAllLocations() {
        List<Location> all = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                all.add(locations[r][c]);
            }
        }
        return all;
    }


    public List<Animal> getAllAnimals() {
        List<Animal> all = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                all.addAll(locations[r][c].getAnimals());
            }
        }
        return all;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Location[][] getLocations() { return locations; }

    @Override
    public String toString() {
        return String.format("Остров(%dx%d)", rows, cols);
    }
}
