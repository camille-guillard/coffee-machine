package project.drinkmaker.orange;

import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;

public interface HotDrinkMaker extends DrinkMaker {

    Drink getDrink(DrinkType drinkType, int numberOfSugar, boolean extraHot);

}
