package project.logic.checker;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkType;
import project.drinkmaker.checker.CheckingDrinkMaker;
import project.drinkmaker.orange.HotDrinkMaker;
import project.drinkmaker.orange.OrangeDrinkType;
import project.logic.stats.StatsDrinkMakerProtocol;

import java.util.Arrays;
import java.util.List;

public class NotifyingDrinkMakerProtocol extends StatsDrinkMakerProtocol implements EmailNotifier {

    protected boolean extraHot = false;

    public NotifyingDrinkMakerProtocol(CheckingDrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(OrangeDrinkType.values()));
    }

    protected NotifyingDrinkMakerProtocol(HotDrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process() {
        if (((CheckingDrinkMaker) drinkMaker).isEmpty(selectedDrinkType.getName())) {
            notifyMissingDrink(selectedDrinkType.getName());
            return null;
        }
        return super.process();
    }

    public void notifyMissingDrink(String drink) {
        System.out.println("Sending email");
    }
}
