package project.logic.orange;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.drinkmaker.orange.OrangeDrinkType;
import project.logic.basic.Command;
import project.logic.money.MoneyDrinkMakerProtocol;

import java.util.Arrays;
import java.util.List;

public class OrangeDrinkMakerProtocol extends MoneyDrinkMakerProtocol {

    public OrangeDrinkMakerProtocol(DrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(OrangeDrinkType.values()));
    }

    protected OrangeDrinkMakerProtocol(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process(Command c) {
        if(c.isHot()) {
            this.command.replace(this.command.substring(0,1), this.command.substring(0,1) + "h");
        }
        return super.process(c);
    }

    protected Drink execute() {
        return drinkMaker.getDrink(command);
    }

}
