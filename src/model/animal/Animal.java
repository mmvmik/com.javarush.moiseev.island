package model.animal;

import model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Animal {

    protected final String name;
    protected final String symbol;
    protected final double weight;          // кг
    protected final int maxPerCell;         // макс. количество на клетке
    protected final int moveSpeed;          // клеток за ход
    protected final double foodNorm;        // кг пищи для насыщения
    protected final double reproductionProbability; // вероятность размножения

    protected double hunger;                // текущий голод (0 = сыт, foodNorm = голоден)
    protected boolean alive;
    protected Location location;
    protected int speciesId;                // для статистики

    protected static final Random RANDOM = new Random();

    public Animal(String name, String symbol, double weight, int maxPerCell,
                  int moveSpeed, double foodNorm, double reproductionProbability) {
        this.name = name;
        this.symbol = symbol;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.moveSpeed = moveSpeed;
        this.foodNorm = foodNorm;
        this.reproductionProbability = reproductionProbability;
        this.hunger = 0;
        this.alive = true;
    }

    public void setSpeciesId(int id) {
        this.speciesId = id;
    }


    public abstract double eat();


    public Animal tryReproduce() {
        if (!alive) return null;
        if (RANDOM.nextDouble() < reproductionProbability) {
            Animal offspring = createOffspring();
            if (offspring != null) {
                return offspring;
            }
        }
        return null;
    }

    protected abstract Animal createOffspring();

 // Выбор направления движения
    public int[] chooseMoveDirection(int maxRows, int maxCols) {
        if (!alive || moveSpeed <= 0) return null;

        int dr = RANDOM.nextInt(moveSpeed * 2 + 1) - moveSpeed; // [-moveSpeed, +moveSpeed]
        int dc = RANDOM.nextInt(moveSpeed * 2 + 1) - moveSpeed;


        if (dr == 0 && dc == 0) return null;

        int newRow = location.getRow() + dr;
        int newCol = location.getCol() + dc;


        newRow = Math.max(0, Math.min(newRow, maxRows - 1));
        newCol = Math.max(0, Math.min(newCol, maxCols - 1));

        return new int[]{newRow, newCol};
    }


    public void moveTo(Location newLocation) {
        this.location = newLocation;
    }


    public void increaseHunger() {
        if (foodNorm <= 0) return;
        this.hunger += 1.0;
        if (this.hunger >= this.foodNorm) {
            this.alive = false;
        }
    }


    protected void satisfyHunger(double eatenKg) {
        this.hunger = Math.max(0, this.hunger - eatenKg);
    }

    public void kill() {
        this.alive = false;
    }

    // Getters
    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public double getWeight() { return weight; }
    public int getMaxPerCell() { return maxPerCell; }
    public int getMoveSpeed() { return moveSpeed; }
    public double getFoodNorm() { return foodNorm; }
    public double getHunger() { return hunger; }
    public boolean isAlive() { return alive; }
    public Location getLocation() { return location; }
    public int getSpeciesId() { return speciesId; }

    @Override
    public String toString() {
        return name + "(" + symbol + ")";
    }
}
