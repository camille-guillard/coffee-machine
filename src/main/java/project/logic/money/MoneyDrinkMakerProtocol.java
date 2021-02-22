package project.logic.money;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.drinkmaker.money.MoneyDrinkType;
import project.drinkmaker.money.PricedDrink;
import project.logic.basic.Command;
import project.logic.basic.BasicDrinkMakerProtocol;
import project.pad.MoneyPad;

import java.util.Arrays;
import java.util.List;

public class MoneyDrinkMakerProtocol extends BasicDrinkMakerProtocol implements MoneyPad {

    protected double pool;

    public MoneyDrinkMakerProtocol(DrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(MoneyDrinkType.values()));
    }

    protected MoneyDrinkMakerProtocol(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    protected Drink process(Command c) {
        if (selectedDrinkType != null) {
            double price = ((PricedDrink) selectedDrinkType).getPrice();
            if (pool < price) {
                throw new NotEnoughMoneyException();
            }
            pool -= price;
        }
        return super.process(c);
    }

    @Override
    public void addMoney(double money) {
        pool += money;
    }

    @Override
    public double getPool() {
        return pool;
    }
}
