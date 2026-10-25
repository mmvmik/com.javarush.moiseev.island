package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super("Гусеница", "\uD83D\uDC1B", 0.01, 1000, 0, 0.0, 0.25);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 90.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Caterpillar();
    }

    @Override
    public double eat() {
        if (!alive || location == null) return 0;

        if (foodNorm <= 0) return 0;

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
