package model.animal.predator;

import model.animal.Animal;


public abstract class Predator extends Animal {

    protected Predator(String name, String symbol, double weight, int maxPerCell,
                       int moveSpeed, double foodNorm, double reproductionProbability) {
        super(name, symbol, weight, maxPerCell, moveSpeed, foodNorm, reproductionProbability);
    }

    public abstract boolean canEat(Animal prey);

    public abstract double getEatProbability(Animal prey);
}
