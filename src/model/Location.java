package model;

import model.animal.Animal;

import java.util.ArrayList;
import java.util.List;


public class Location {

    private final int row;
    private final int col;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }


    public boolean addAnimal(Animal animal) {
        if (!animal.isAlive()) return false;
        if (animals.size() >= getMaxCapacity()) return false;
        animal.moveTo(this);
        animals.add(animal);
        return true;
    }


    public void removeDeadAnimals() {
        animals.removeIf(a -> !a.isAlive());
    }


    public void removeDeadPlants() {
        plants.removeIf(p -> !p.isAlive());
    }


    public void addPlant(Plant plant) {
        if (plant.isAlive()) {
            plant.setLocation(this);
            plants.add(plant);
        }
    }

    private int getMaxCapacity() {
        int minCapacity = Integer.MAX_VALUE;
        for (Animal animal : animals) {
            minCapacity = Math.min(minCapacity, animal.getMaxPerCell());
        }
        return minCapacity == Integer.MAX_VALUE ? 10 : minCapacity;
    }


    public int getRow() { return row; }
    public int getCol() { return col; }
    public List<Animal> getAnimals() { return animals; }
    public List<Plant> getPlants() { return plants; }
    public int getAnimalCount() { return animals.size(); }
    public int getPlantCount() { return plants.size(); }

    @Override
    public String toString() {
        return String.format("[%d,%d] A:%d P:%d", row, col, animals.size(), plants.size());
    }
}
