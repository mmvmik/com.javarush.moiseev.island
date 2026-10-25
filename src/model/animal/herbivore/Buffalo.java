package model.animal.herbivore;

import model.animal.Animal;
import model.Plant;


public class Buffalo extends Herbivore {

    public Buffalo() {
        super("Буйвол", "\uD83D\uDC03", 700.0, 10, 3, 100.0, 0.02);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 68.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Buffalo();
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
