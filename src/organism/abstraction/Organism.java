package organism.abstraction;

import organism.Location;

public abstract class Organism {

    protected String name;
    protected String icon;
    protected int health;
    protected double weigth;
    protected int limitInArea;
    protected boolean iaAlive;
    protected Location location;

    public Organism(
            String name,
            String icon,
            int health,
            double weigth,
            int limitInArea,
            boolean iaAlive,
            Location location)
    {
        this.name = name;
        this.icon = icon;
        this.health = health;
        this.weigth = weigth;
        this.limitInArea = limitInArea;
        this.iaAlive = iaAlive;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public int getLimitInArea() {
        return limitInArea;
    }

    public void setLimitInArea(int limitInArea) {
        this.limitInArea = limitInArea;
    }

    public boolean isIaAlive() {
        return iaAlive;
    }

    public void setIaAlive(boolean iaAlive) {
        this.iaAlive = iaAlive;
    }
}

