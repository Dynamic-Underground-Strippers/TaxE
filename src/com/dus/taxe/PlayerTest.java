package com.dus.taxe;

import junit.framework.TestCase;
import org.junit.Test;

public class PlayerTest extends TestCase {
    @Test
    public void testAddTrain() throws Exception {
        Player newPlayer = new Player("Bla");
        assertEquals(0, newPlayer.trainSize());


        newPlayer.addTrain();
        assertEquals(1, newPlayer.trainSize());

        newPlayer.removeTrain(0);
        assertEquals(0, newPlayer.trainSize());
    }

    @Test
    public void testMoreTrain() throws Exception {
        Player newPlayer = new Player("Testie");

        newPlayer.addTrain();
        Train newTrain = newPlayer.getTrain(0);

        assertEquals(newPlayer.getTrainIndex(newTrain), 0);


    }

    @Test
    public void testUpgrades() throws Exception {
        Player newPlayer = new Player("Testie2");
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();

        assertTrue(newPlayer.hasMaxUpgrades());
    }

    @Test
    public void testDiscardUpgrade() throws Exception {

        Player newPlayer = new Player("Raluca");
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        assertEquals(4, newPlayer.upgradeSize());

        newPlayer.discardRandUpgrade();
        assertEquals(3, newPlayer.upgradeSize());


    }

    @Test
    public void testHasMaxUpgrades() throws Exception {
        Player newPlayer = new Player("Raluca");
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        newPlayer.giveRandomUpgrade();
        assertEquals(newPlayer.hasMaxUpgrades(), false);

        newPlayer.giveRandomUpgrade();
        assertEquals(newPlayer.hasMaxUpgrades(), true);


    }

    @Test
    public void testGiveRandEngine() throws Exception {
        Player newPlayer = new Player("Raluca");
        newPlayer.giveRandomEngine();
        newPlayer.giveRandomEngine();
        assertEquals(newPlayer.hasMaxEngines(), false);

        newPlayer.giveRandomEngine();
        assertEquals(newPlayer.hasMaxEngines(), true);
    }
    @Test
    public void discardRandEngine() throws Exception{
        Player newPlayer = new Player ("Raluca");
        newPlayer.giveRandomEngine();
        newPlayer.giveRandomEngine();
        newPlayer.giveRandomEngine();
        assertEquals(newPlayer.hasMaxEngines(), true);

        newPlayer.discardRandEngine();
        assertEquals(newPlayer.hasMaxEngines(), false);

    }

}