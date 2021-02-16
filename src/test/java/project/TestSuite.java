package project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import project.logic.basic.BasicDrinkMakerProtocolTest;
import project.logic.checker.NotifyingDrinkMakerProtocolTest;
import project.logic.money.MoneyDrinkMakerProtocolTest;
import project.logic.orange.OrangeDrinkMakerProtocolTest;
import project.logic.stats.StatsDrinkMakerProtocolTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicDrinkMakerProtocolTest.class,
        MoneyDrinkMakerProtocolTest.class,
        OrangeDrinkMakerProtocolTest.class,
        StatsDrinkMakerProtocolTest.class,
        NotifyingDrinkMakerProtocolTest.class
})

public class TestSuite {

}
