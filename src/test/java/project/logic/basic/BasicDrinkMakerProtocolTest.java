package project.logic.basic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
import project.drinkmaker.DrinkType;
import project.logic.CoffeeMachine;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


public class BasicDrinkMakerProtocolTest {

    @Mock
    DrinkMaker drinkMaker = mock(DrinkMaker.class);

    @Mock
    Drink drink = mock(Drink.class);

    @Before
    public void before() {
        doReturn(drink).when(drinkMaker).getDrink(any(DrinkType.class), anyInt());
    }

    @Test
    public void testGetDrink() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("T:1:0");
        assertNotNull(d);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetDrink2() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("A:1:0");
    }

    @Test
    public void testGetDrink3() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("M:Test");
        assertNull(d);
    }

}
