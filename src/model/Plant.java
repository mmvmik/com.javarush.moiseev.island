package model;


public class Plant {

    private final String symbol;
    private double weight;        // кг
    private boolean alive;
    private Location location;

    public Plant(double initialWeight) {
        this.symbol = "\uD83C\uDF3F";
        this.weight = initialWeight;
        this.alive = true;
    }


    public void grow(double growthRate) {
        if (alive) {
            this.weight += growthRate;
        }
    }

    public void die() {
        this.alive = false;
    }

    // Getters
    public String getSymbol() { return symbol; }
    public double getWeight() { return weight; }
    public boolean isAlive() { return alive; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
