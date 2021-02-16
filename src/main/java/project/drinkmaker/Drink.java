package project.drinkmaker;

import project.drinkmaker.basic.BasicDrinkType;

public interface Drink {

    String getName();

    BasicDrinkType getDrinkType();

    int getNumberOfSugar();

    void addSugar(int n);

    boolean hasStick();

    boolean isExtraHot();

}
