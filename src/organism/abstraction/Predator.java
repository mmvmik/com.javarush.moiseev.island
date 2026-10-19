package organism.abstraction;

import organism.Location;

public abstract class Predator extends Animal{
    public Predator(
            String name,
            String icon,
            int health,
            double weigth,
            int limitInArea,
            boolean iaAlive,
            Location location,
            int speed,
            boolean currentSatiety,
            double satietyLimitPerEat)
    {
        super(
                name,
                icon,
                health,
                weigth,
                limitInArea,
                iaAlive,
                location,
                speed,
                currentSatiety,
                satietyLimitPerEat);
    }
}
