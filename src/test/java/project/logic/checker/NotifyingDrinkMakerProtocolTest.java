package project.logic.checker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import project.drinkmaker.Drink;
import project.drinkmaker.DrinkType;
import project.drinkmaker.checker.CheckingDrinkMaker;
import project.logic.CoffeeMachine;
import project.logic.basic.Command;
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
        doReturn(drink).when(drinkMaker).getDrink(AdditionalMatchers.not(startsWith("M")));
        doReturn(null).when(drinkMaker).getDrink(startsWith("M"));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void basicCommandTest() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
    }

    @Test(expected = NoSuchElementException.class)
    public void commandNotFoundTest() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("A");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);    }

    @Test
    public void messageCommandTest() {
        CoffeeMachine coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Command c = new Command();
        c.setName("M");
        c.setMessage("Test");
        Drink d = coffeeMachine.getDrink(c);
        assertNull(d);
    }

    @Test
    public void moneyTest() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
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
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("T");
        c.setNumberOfSugar(1);
        coffeeMachine.getDrink(c);
        coffeeMachine.getDrink(c);
        coffeeMachine.getDrink(c);
        assertEquals(0.6, coffeeMachine.getPool(), 1e-15);
    }

    @Test
    public void getOrangeTest() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("O");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
    }

    @Test
    public void getHotTeaTest() {
        MoneyDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("O");
        c.setNumberOfSugar(1);
        c.setHot(true);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
    }

    @Test
    public void statsTests() {
        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        Command c1 = new Command();
        c1.setName("T");
        c1.setNumberOfSugar(1);
        c1.setHot(true);

        Command c2 = new Command();
        c2.setName("T");
        c2.setNumberOfSugar(1);

        Command c3 = new Command();
        c3.setName("O");
        c3.setNumberOfSugar(1);

        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink(c1);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink(c1);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink(c2);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink(c2);
        coffeeMachine.addMoney(1.0);
        coffeeMachine.getDrink(c3);
        coffeeMachine.printStats();
    }

    @Test
    public void fullPoolNotifyingTest() {
        doReturn(false).when(drinkMaker).isEmpty(anyString());

        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("O");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNotNull(d);
        assertEquals(0.4, coffeeMachine.getPool(), 1e-15);
    }

    @Test
    public void emptyPoolNotifyingTest() {
        doReturn(true).when(drinkMaker).isEmpty(anyString());

        StatsDrinkMakerProtocol coffeeMachine = new NotifyingDrinkMakerProtocol(drinkMaker);
        coffeeMachine.addMoney(1.0);
        Command c = new Command();
        c.setName("O");
        c.setNumberOfSugar(1);
        Drink d = coffeeMachine.getDrink(c);
        assertNull(d);
        assertEquals(1.0, coffeeMachine.getPool(), 1e-15);
    }

}
