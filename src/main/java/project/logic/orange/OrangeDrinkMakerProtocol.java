package project.logic.orange;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkType;
import project.drinkmaker.orange.HotDrinkMaker;
import project.drinkmaker.orange.OrangeDrinkType;
import project.logic.money.MoneyDrinkMakerProtocol;

import java.util.Arrays;
import java.util.List;

public class OrangeDrinkMakerProtocol extends MoneyDrinkMakerProtocol {

    protected boolean extraHot = false;

    public OrangeDrinkMakerProtocol(HotDrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(OrangeDrinkType.values()));
    }

    protected OrangeDrinkMakerProtocol(HotDrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process() {
        extraHot = command[0] != null && command[0].contains("h");
        return super.process();
    }

    protected Drink execute() {
        return ((HotDrinkMaker) drinkMaker).getDrink(selectedDrinkType, numberOfSugar, extraHot);
    }

}
