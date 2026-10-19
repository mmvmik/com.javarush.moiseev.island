package organism.herbivore;

import organism.Location;
import organism.abstraction.Herbivore;

public class Hourse extends Herbivore {
    public Hourse() {
        super("Hourse",
                "\uD83C\uDF31",
                23,
                200,
                200,
                true,
                null,
                34,
                23
                );
         }

    @Override
    public String toString() {
        return "Hourse{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
