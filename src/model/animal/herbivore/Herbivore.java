package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;


public abstract class Herbivore extends Animal {

    protected Herbivore(String name, String symbol, double weight, int maxPerCell,
                        int moveSpeed, double foodNorm, double reproductionProbability) {
        super(name, symbol, weight, maxPerCell, moveSpeed, foodNorm, reproductionProbability);
    }

    public abstract boolean canEatPlant(Plant plant);

    public abstract double getEatProbability(Plant plant);
}
