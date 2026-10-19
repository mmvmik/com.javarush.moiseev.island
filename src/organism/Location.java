package organism;

import organism.abstraction.Animal;
import organism.abstraction.Plant;

import java.util.ArrayList;

public class Location {
private ArrayList <Animal> animals;
private ArrayList <Plant> plant;

    public Location(ArrayList<Animal> animals, ArrayList<Plant> plant) {
        this.animals = animals;
        this.plant = plant;
    }
}
