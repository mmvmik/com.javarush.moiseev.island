package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;


public class Mouse extends Herbivore {

    public Mouse() {
        super("Мышь", "\uD83D\uDC01", 0.05, 500, 1, 0.01, 0.20);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 85.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Mouse();
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
