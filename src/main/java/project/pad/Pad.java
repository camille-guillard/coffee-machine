package project.pad;

import project.drinkmaker.Drink;
import project.logic.basic.Command;

public interface Pad {

    Drink getDrink(Command c);

}
