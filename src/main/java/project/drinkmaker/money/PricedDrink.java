package project.drinkmaker.money;

import project.drinkmaker.DrinkType;

public interface PricedDrink extends DrinkType {
    double getPrice();
}
