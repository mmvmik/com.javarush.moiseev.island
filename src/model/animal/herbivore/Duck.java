package model.animal.herbivore;

import model.FoodChart;
import model.animal.Animal;
import model.Plant;

public class Duck extends Herbivore {

    public Duck() {
        super("Утка", "\uD83E\uDD86", 1.0, 200, 4, 0.15, 0.10);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 82.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Duck();
    }

    @Override
    public double eat() {
        if (!alive || location == null) return 0;

        for (Animal prey : location.getAnimals()) {
            if (prey != this && prey.isAlive() && FoodChart.canEat(this.getClass(), prey.getClass())) {
                double prob = FoodChart.getEatProbability(this.getClass(), prey.getClass()) / 100.0;
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


        for (Plant plant : location.getPlants()) {
            if (plant.isAlive() && canEatPlant(plant)) {
                double prob = getEatProbability(plant) / 100.0;
                if (Math.random() < prob) {
                    double eaten = Math.min(plant.getWeight(), foodNorm - hunger);
                    if (eaten > 0) {
                        satisfyHunger(eaten);
                        plant.die();
                        return eaten;
                    }
                }
            }
        }
        return 0;
    }
}
