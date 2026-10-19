package organism.abstraction;

import organism.Location;

public abstract class Animal extends Organism {


    protected int speed;
    protected boolean currentSatiety;
    protected double satietyLimitPerEat;

    public Animal(
            String name,
            String icon,
            int health,
            double weigth,
            int limitInArea,
            boolean iaAlive,
            Location location,
            int speed,
            boolean currentSatiety,
            double satietyLimitPerEat
            ) {
        super(
                name,
                icon,
                health,
                weigth,
                limitInArea,
                iaAlive,
                location
        );
        this.speed = speed;
        this.currentSatiety = currentSatiety;
        this.satietyLimitPerEat = satietyLimitPerEat;
    }


}
