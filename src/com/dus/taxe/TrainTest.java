package com.dus.taxe;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class TrainTest extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNewTrain() throws Exception {
        Train myTrain = new Train();
        assertEquals("Hand Cart", myTrain.getEngine().getName()); //confirms trains start with hand cart
    }

    @Test
    public void testAssociateRoute() throws Exception {
        //This still must be written once Game and Route classes have been finished
    }

    @Test
    public void testGetSpeed() throws Exception {
        Train myTrain = new Train();
        int speed = myTrain.getSpeed();
        int expected = 15;
        assertEquals(expected, speed);
    }

    @Test
    public void testAddUpgrade() throws Exception {
        Train myTrain = new Train();
        boolean beforeUpgrade = myTrain.hasUpgrade("Double Speed");
        assertFalse(beforeUpgrade);

        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        myTrain.addUpgrade(myUpgrade);
        boolean hasUpgrade = myTrain.hasUpgrade("Double Speed");
        assertTrue(hasUpgrade);
    }

    @Test()
    public void testAddUpgradeWhenUpgradeAlreadyUsed() throws Exception {
        Train myTrain = new Train();
        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        myTrain.addUpgrade(myUpgrade);
        boolean hasUpgrade = myTrain.hasUpgrade("Double Speed");
        assertTrue(hasUpgrade);

        Upgrade anotherUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);

        try {
            myTrain.addUpgrade(anotherUpgrade);
            fail("Should have thrown exception");
        } catch (UnsupportedOperationException e) {
            assertTrue(true); //exception raised correctly
        }

    }

    @Test
    public void testSetSpeed() throws Exception {
        Train myTrain = new Train();
        int expected = 15;
        assertEquals(expected, myTrain.getSpeed());
        myTrain.setSpeed(150);
        expected = 150;
        assertEquals(expected, myTrain.getSpeed());
    }

    @Test
    public void testGetEngine() throws Exception {
        Train myTrain = new Train();
        Engine myEngine = new Engine(Engine.EngineType.DIESEL);
        myTrain.setEngine(myEngine);
        assertSame(myEngine, myTrain.getEngine());
    }

    @Test
    public void testSetEngine() throws Exception {
        Train myTrain = new Train();
        Engine newEngine = new Engine(Engine.EngineType.ELECTRIC);
        myTrain.setEngine(newEngine);
        assertSame(newEngine, myTrain.getEngine()); //confirms new engine added
    }

    @Test
    public void testReapplyingWithNewEngine() throws Exception {
        Train myTrain = new Train();
        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        myTrain.addUpgrade(myUpgrade);
        int expected = 30;
        assertEquals(expected, myTrain.getSpeed()); //confirms double speed applied correctly

        Engine myEngine = new Engine(Engine.EngineType.ELECTRIC);
        myTrain.setEngine(myEngine);
        expected = 150;
        assertEquals(expected, myTrain.getSpeed()); //confirms double speed applied again after new engine


    }

    @Test
    public void testGetUpgrades() throws Exception {
        Train myTrain = new Train();
        Upgrade upgrade1 = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        Upgrade upgrade2 = new Upgrade(Upgrade.UpgradeType.TELEPORT);
        ArrayList<Upgrade> myList = new ArrayList<Upgrade>();
        assertEquals(myList, myTrain.getUpgrades()); //confirms empty arraylist for no upgrades

        myTrain.addUpgrade(upgrade1);
        myTrain.addUpgrade(upgrade2);
        myList.add(upgrade1);
        myList.add(upgrade2);
        assertEquals(myList, myTrain.getUpgrades()); //confirms arraylist has two added upgrades
    }

    @Test
    public void testGetVisitedNodes() throws Exception {
        //This still must be written once Game class has been finished
    }

    @Test
    public void testHasUpgrade() throws Exception {
        Train myTrain = new Train();
        assertFalse(myTrain.hasUpgrade("Double Speed")); //confirms return false when doesn't have upgrade

        Upgrade upgrade1 = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        myTrain.addUpgrade(upgrade1);
        assertTrue(myTrain.hasUpgrade("Double Speed"));
    }

    @Test
    public void testGetRoute() throws Exception {
        //Wait for route and game classes to be completed.
    }



}