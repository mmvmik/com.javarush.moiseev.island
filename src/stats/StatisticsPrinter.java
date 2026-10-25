package stats;

import model.Island;
import model.animal.Animal;

import java.util.LinkedHashMap;
import java.util.Map;


public class StatisticsPrinter {

    public static void printStatistics(Island island, int tick) {
        Map<String, Integer> speciesCount = new LinkedHashMap<>();
        Map<String, Double> speciesWeight = new LinkedHashMap<>();
        Map<String, String> speciesSymbol = new LinkedHashMap<>();
        int totalAnimals = 0;
        int totalPlants = 0;
        int aliveAnimals = 0;

        for (Animal animal : island.getAllAnimals()) {
            speciesCount.merge(animal.getName(), 1, Integer::sum);
            speciesWeight.merge(animal.getName(), animal.getWeight(), Double::sum);
            speciesSymbol.merge(animal.getName(), animal.getSymbol(), (old, newVal) -> old);
            totalAnimals++;
            if (animal.isAlive()) {
                aliveAnimals++;
            }
        }

        for (int r = 0; r < island.getRows(); r++) {
            for (int c = 0; c < island.getCols(); c++) {
                totalPlants += island.getLocation(r, c).getPlantCount();
            }
        }

        System.out.println("========== День " + tick + " ==========");
        System.out.println("Остров: " + island.getRows() + "x" + island.getCols());
        System.out.println("Всего животных: " + totalAnimals + " (Живых: " + aliveAnimals + ")");
        System.out.println("Всего растений: " + totalPlants);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %8s %s%n", "Тип", "Колличество", "Symbol");
        System.out.println("--------------------------------------------------");

        for (Map.Entry<String, Integer> entry : speciesCount.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            String symbol = speciesSymbol.getOrDefault(name, "");
            System.out.printf("%-20s %8d %8s %n", name, count, symbol);
        }

        System.out.println("==================================================\n");
    }
}
