package project.logic.money;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.logic.CoffeeMachine;


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
        doReturn(drink).when(drinkMaker).getDrink(any(DrinkType.class), anyInt());
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void testGetDrink() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("T:1:0");
        assertNotNull(d);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetDrink2() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("A:1:0");
        assertNull(d);
    }

    @Test
    public void testGetDrink3() {
        CoffeeMachine coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("M:Test");
        assertNull(d);
    }

    @Test
    public void testGetDrink4() {
        MoneyDrinkMakerProtocol coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("T:1:0");
        assertNotNull(d);
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void testGetDrink5() {
        MoneyDrinkMakerProtocol coffeeMachine = new MoneyDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.getDrink("T:1:0");
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

}
