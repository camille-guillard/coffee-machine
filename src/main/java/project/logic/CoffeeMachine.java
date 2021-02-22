package project.logic;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.logic.basic.Command;

import java.util.List;

public abstract class CoffeeMachine {

    protected String SPLIT_CHARACTER = ":";

    protected DrinkMaker drinkMaker;
    protected List<? extends DrinkType> catalog;

    protected CoffeeMachine(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        this.drinkMaker = drinkMaker;
        this.catalog = catalog;
    }

    public abstract Drink getDrink(Command c);

}
