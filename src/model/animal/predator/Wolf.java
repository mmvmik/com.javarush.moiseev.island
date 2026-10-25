package model.animal.predator;

import model.FoodChart;
import model.animal.Animal;

public class Wolf extends Predator {

    public Wolf() {
        super("Волк", "\uD83D\uDC3A", 50.0, 30, 3, 8.0, 0.05);
    }

    @Override
    public boolean canEat(Animal prey) {
        return FoodChart.canEat(this.getClass(), prey.getClass());
    }

    @Override
    public double getEatProbability(Animal prey) {
        return FoodChart.getEatProbability(this.getClass(), prey.getClass());
    }

    @Override
    protected Animal createOffspring() {
        return new Wolf();
    }

    @Override
    public double eat() {
        if (!alive || location == null) return 0;

        for (Animal prey : location.getAnimals()) {
            if (prey != this && prey.isAlive() && canEat(prey)) {
                double prob = getEatProbability(prey) / 100.0;
                if (Math.random() < prob) {
                    double eaten = Math.min(prey.getWeight(), foodNorm - hunger);
                    if (eaten > 0) {
                        satisfyHunger(eaten);
                        prey.kill();
                        return eaten;
                    }
                }
            }
        }
        return 0;
    }
}
