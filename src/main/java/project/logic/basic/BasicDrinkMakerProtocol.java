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

    protected String[] command;
    protected DrinkType selectedDrinkType;
    protected int numberOfSugar = 0;

    public BasicDrinkMakerProtocol(DrinkMaker drinkMaker) {
        this(drinkMaker, Arrays.asList(BasicDrinkType.values()));
    }

    protected BasicDrinkMakerProtocol(DrinkMaker drinkMaker, List<? extends DrinkType> catalog) {
        super(drinkMaker, catalog);
    }

    public Drink getDrink(String s) throws NotEnoughMoneyException {
        try {
            if(initCommand(s)) {
                return process();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw e;
        }
        return null;
    }

    protected boolean initCommand(String s) {
        try {
            command = s.split(SPLIT_CHARACTER);

            if ("M".equals(command[0])) {
                System.out.println(command[1]);
                return false;
            } else {
                this.selectedDrinkType = catalog.stream()
                        .filter(d -> d.getLogo().charAt(0) == (command[0].charAt(0)))
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);

                setNumberOfSugar(command[1]);
                return true;
            }

        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw e;
        }
    }

    protected void setNumberOfSugar(String s) {
        numberOfSugar = 0;
        try {
            numberOfSugar = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            numberOfSugar = 0;
        }
    }

    protected Drink process() {
        if (selectedDrinkType != null) {
            return execute();
        }
        return null;
    }

    protected Drink execute() {
        return drinkMaker.getDrink(selectedDrinkType, numberOfSugar);
    }

}
