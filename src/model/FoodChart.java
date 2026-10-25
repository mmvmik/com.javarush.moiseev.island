package model;

import java.util.HashMap;
import java.util.Map;


public class FoodChart {

    private static final Map<String, Map<String, Double>> FOOD_TABLE = new HashMap<>();

    static {
        // Волк
        Map<String, Double> wolfFood = new HashMap<>();
        wolfFood.put("Horse", 10.0);
        wolfFood.put("Deer", 15.0);
        wolfFood.put("Rabbit", 60.0);
        wolfFood.put("Mouse", 80.0);
        wolfFood.put("Goat", 60.0);
        wolfFood.put("Sheep", 70.0);
        FOOD_TABLE.put("Wolf", wolfFood);

        // Удав
        Map<String, Double> boaFood = new HashMap<>();
        boaFood.put("Fox", 15.0);
        boaFood.put("Rabbit", 20.0);
        boaFood.put("Mouse", 40.0);
        FOOD_TABLE.put("BoaConstrictor", boaFood);

        // Лиса
        Map<String, Double> foxFood = new HashMap<>();
        foxFood.put("Rabbit", 70.0);
        foxFood.put("Mouse", 90.0);
        FOOD_TABLE.put("Fox", foxFood);

        // Медведь
        Map<String, Double> bearFood = new HashMap<>();
        bearFood.put("BoaConstrictor", 80.0);
        bearFood.put("Horse", 40.0);
        bearFood.put("Deer", 80.0);
        bearFood.put("Rabbit", 80.0);
        bearFood.put("Mouse", 90.0);
        bearFood.put("Goat", 70.0);
        bearFood.put("Sheep", 70.0);
        FOOD_TABLE.put("Bear", bearFood);

        // Орел
        Map<String, Double> eagleFood = new HashMap<>();
        eagleFood.put("Fox", 10.0);
        eagleFood.put("Rabbit", 90.0);
        eagleFood.put("Mouse", 90.0);
        FOOD_TABLE.put("Eagle", eagleFood);

        // Кабан
        Map<String, Double> boarFood = new HashMap<>();
        boarFood.put("Mouse", 50.0);
        FOOD_TABLE.put("Boar", boarFood);

        // Утка ест гусениц
        Map<String, Double> duckFood = new HashMap<>();
        duckFood.put("Caterpillar", 50.0);
        FOOD_TABLE.put("Duck", duckFood);
    }


    public static double getEatProbability(String predatorName, String preyName) {
        Map<String, Double> preyMap = FOOD_TABLE.get(predatorName);
        if (preyMap == null) return 0.0;
        return preyMap.getOrDefault(preyName, 0.0);
    }

    public static double getEatProbability(Class<?> predatorClass, Class<?> preyClass) {
        return getEatProbability(predatorClass.getSimpleName(), preyClass.getSimpleName());
    }

//     Проверить, есть ли данные о поедании.

    public static boolean canEat(String predatorName, String preyName) {
        return getEatProbability(predatorName, preyName) > 0.0;
    }

    public static boolean canEat(Class<?> predatorClass, Class<?> preyClass) {
        return canEat(predatorClass.getSimpleName(), preyClass.getSimpleName());
    }
}
