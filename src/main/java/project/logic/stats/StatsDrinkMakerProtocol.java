package project.logic.stats;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkType;
import project.drinkmaker.orange.HotDrinkMaker;
import project.drinkmaker.orange.OrangeDrinkType;
import project.logic.orange.OrangeDrinkMakerProtocol;

import java.util.*;

public class StatsDrinkMakerProtocol extends OrangeDrinkMakerProtocol implements statsTracking {

    protected Map<String, Integer> stats = new TreeMap<>();

    public StatsDrinkMakerProtocol(HotDrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(OrangeDrinkType.values()));
    }

    protected StatsDrinkMakerProtocol(HotDrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process() {
        Integer n = 0;
        String name = selectedDrinkType.getName();
        if(stats.containsKey(name)) {
            stats.put(name, stats.get(name) + 1);
        } else {
            stats.put(name, 1);
        }
        return super.process();
    }

    @Override
    public void printStats() {
        stats.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
