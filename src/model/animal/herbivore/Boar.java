package model.animal.herbivore;

import model.FoodChart;
import model.animal.Animal;
import model.Plant;


public class Boar extends Herbivore {

    public Boar() {
        super("Кабан", "\uD83D\uDC17", 400.0, 50, 2, 50.0, 0.04);
    }

    @Override
    public boolean canEatPlant(Plant plant) {
        return true;
    }

    @Override
    public double getEatProbability(Plant plant) {
        return 72.0;
    }

    @Override
    protected Animal createOffspring() {
        return new Boar();
    }

    @Override
    public double eat() {
        if (!alive || location == null) return 0;

        // Сначала пытаемся съесть мышей
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

        // Затем растения
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
