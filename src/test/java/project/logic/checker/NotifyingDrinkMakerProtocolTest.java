package project.logic.checker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkType;
import project.drinkmaker.checker.CheckingDrinkMaker;
import project.logic.CoffeeMachine;
import project.logic.money.MoneyDrinkMakerProtocol;
import project.logic.money.NotEnoughMoneyException;
import project.logic.stats.StatsDrinkMakerProtocol;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class NotifyingDrinkMakerProtocolTest {

    @Mock
    CheckingDrinkMaker drinkMaker = mock(CheckingDrinkMaker.class);

    @Mock
    Drink drink = mock(Drink.class);

    @Before
    public void before() {
        doReturn(drink).when(drinkMaker).getDrink(any(DrinkType.class), anyInt(), anyBoolean());
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void testGetDrink() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("T:1:0");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetDrink2() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("A:1:0");
    }

    @Test
    public void testGetDrink3() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Drink d = coffeeMachine.getDrink("M:Test");
        assertNull(d);
    }

    @Test
    public void testGetDrink4() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("T:1:0");
        assertNotNull(d);
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void testGetDrink5() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.getDrink("T:1:0");
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

    @Test
    public void testGetDrink6() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("O:1:0");
        assertNotNull(d);
    }

    @Test
    public void testGetDrink7() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("Th:1:0");
        assertNotNull(d);
    }

    @Test
    public void testGetDrink8() {
        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("Th:1:0");
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("Th:1:0");
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("T:1:0");
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink("O:1:0");
        coffeeMachine.printStats();
    }

    @Test
    public void testGetDrink9() {
        doReturn(false).when(drinkMaker).isEmpty(anyString());

        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("O:1:0");
        assertNotNull(d);
        assertEquals(0.4, coffeeMachine.getPool(), 1e-15);
    }

    @Test
    public void testGetDrink10() {
        doReturn(true).when(drinkMaker).isEmpty(anyString());

        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Drink d = coffeeMachine.getDrink("O:1:0");
        assertNull(d);
        assertEquals(1.0, coffeeMachine.getPool(), 1e-15);
    }

}
