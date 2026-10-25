package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super("Кролик", "\uD83D\uDC07", 2.0, 150, 2, 0.45, 0.15);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 80.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Rabbit();
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
