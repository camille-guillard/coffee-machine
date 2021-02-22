package project.logic.checker;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.drinkmaker.checker.CheckingDrinkMaker;
import project.drinkmaker.orange.OrangeDrinkType;
import project.logic.basic.Command;
import project.logic.stats.StatsDrinkMakerProtocol;

import java.util.Arrays;
import java.util.List;

public class NotifyingDrinkMakerProtocol extends StatsDrinkMakerProtocol implements EmailNotifier {

    protected boolean extraHot = false;

    public NotifyingDrinkMakerProtocol(CheckingDrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(OrangeDrinkType.values()));
    }

    protected NotifyingDrinkMakerProtocol(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process(Command c) {
        if (selectedDrinkType != null && ((CheckingDrinkMaker) drinkMaker).isEmpty(selectedDrinkType.getName())) {
            notifyMissingDrink(selectedDrinkType.getName());
            return null;
        }
        return super.process(c);
    }

    public void notifyMissingDrink(String drink) {
        System.out.println("Sending email");
    }
}
