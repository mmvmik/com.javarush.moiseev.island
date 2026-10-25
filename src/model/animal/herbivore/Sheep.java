package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;

public class Sheep extends Herbivore {

    public Sheep() {
        super("Овца", "\uD83D\uDC11", 70.0, 140, 3, 15.0, 0.05);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 76.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Sheep();
    }

    @Override
    public double eat() {
        if (!alive || location == null) return 0;

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
