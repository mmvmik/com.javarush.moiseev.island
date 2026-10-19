import config.MyWorldConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static void main() {

        MyWorldConfig config = new MyWorldConfig();
        HashMap<String, Integer> animalMap = config.maxQuantityAnimal();
        ArrayList<String> animalList = new ArrayList<>();
        int i=0;
        for (String key: animalMap.keySet())
        {
            animalList.add(i,key);
            i++;
        }
        System.out.println(animalList);
        int s = animalMap.get(animalList.get(1));
        System.out.println(s);
        for (int j = 0; j < s; j++) {


        }

    }
}
