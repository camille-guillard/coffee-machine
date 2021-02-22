package project.logic.basic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkMaker;
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
        doReturn(drink).when(drinkMaker).getDrink(AdditionalMatchers.not(startsWith("M")));
        doReturn(null).when(drinkMaker).getDrink(startsWith("M"));
    }

    @Test
    public void basicCommandTest() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
    }

    @Test(expected = NoSuchElementException.class)
    public void commandNotFoundTest() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("A");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
    }

    @Test
    public void messageCommandTest() {
        CoffeeMachine coffeeMachine = new BasicDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("M");
        c.setMessage("Test");
        Drink d = coffeeMachine.getDrink(c);
        assertNull(d);
    }

}
