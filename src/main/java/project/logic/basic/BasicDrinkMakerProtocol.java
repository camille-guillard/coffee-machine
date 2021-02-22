package project.logic.basic;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.drinkmaker.basic.BasicDrinkType;
import project.logic.CoffeeMachine;
import project.logic.money.NotEnoughMoneyException;
import project.pad.Pad;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BasicDrinkMakerProtocol extends CoffeeMachine implements Pad {

    protected String command;
    protected DrinkType selectedDrinkType;
    protected int numberOfSugar = 0;

    public BasicDrinkMakerProtocol(DrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(BasicDrinkType.values()));
    }

    protected BasicDrinkMakerProtocol(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    public Drink getDrink(Command c) throws NotEnoughMoneyException {
        try {
            if(initCommand(c)) {
                return process(c);
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw e;
        }
        return null;
    }

    protected boolean initCommand(Command c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c.getName());
        sb.append(SPLIT_CHARACTER);

        if("M".equals(c.getName())) {
            sb.append(c.getMessage());
        } else {
            try {
                    this.selectedDrinkType = catalog.stream()
                            .filter(d -> d.getName().charAt(0) == (c.getName().charAt(0)))
                            .findFirst()
                            .orElseThrow(NoSuchElementException::new);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw e;
            }

            sb.append(c.getNumberOfSugar());
            sb.append(SPLIT_CHARACTER);
            sb.append(0);
        }
        this.command = sb.toString();
        return true;
    }


    protected Drink process(Command c) {
        return execute();
    }

    protected Drink execute() {
        return drinkMaker.getDrink(command);
    }

}
