package com.dus.taxe;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpgradeTest extends TestCase {

    //Test return name correctly
    @Test
    public void testGetName() throws Exception {
        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        String name = myUpgrade.getName();
        assertSame("Double Speed", name);
    }


    //Test return reapply correctly
    @Test
    public void testGetReapply() throws Exception {
        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.TELEPORT);
        boolean Reapply = myUpgrade.getReapply();
        assertFalse(Reapply);
    }

    //Test return description correctly
    @Test
    public void testGetDescription() throws Exception {
        Upgrade myUpgrade = new Upgrade(Upgrade.UpgradeType.DOUBLE_SPEED);
        String desc = myUpgrade.getDescription();
        String expected = "This upgrade doubles the speed of one of your trains!";
        assertSame(expected, desc);
    }




}