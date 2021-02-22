package project.logic.money;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.logic.CoffeeMachine;
import project.logic.basic.Command;


import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MoneyDrinkMakerProtocolTest {

    @Mock
    DrinkMaker drinkMaker = mock(DrinkMaker.class);

    @Mock
    Drink drink = mock(Drink.class);

    @Before
    public void before() {
        doReturn(drink).when(drinkMaker).getDrink(AdditionalMatchers.not(startsWith("M")));
        doReturn(null).when(drinkMaker).getDrink(startsWith("M"));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void basicCommandTest() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
    }

    @Test(expected = NoSuchElementException.class)
    public void commandNotFoundTest() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("A");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
    }

    @Test
    public void messageCommandTest() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("M");
        c.setMessage("Test");
        Drink d = coffeeMachine.getDrink(c);
        assertNull(d);
    }

    @Test
    public void moneyTest() {
        MoneyDrinkMakerProtocol coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void notEnoughMoneyTest() {
        MoneyDrinkMakerProtocol coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        coffeeMachine.getDrink(c);
        coffeeMachine.getDrink(c);
        coffeeMachine.getDrink(c);
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

}
