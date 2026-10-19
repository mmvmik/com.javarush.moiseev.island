package organism.abstraction;

public class Plant extends Organism {
    public Plant() {
        super(
                "Растение",
                "\uD83C\uDF31",
                100, 
                1,
                true,
                null);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
