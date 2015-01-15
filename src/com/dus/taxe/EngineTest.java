package com.dus.taxe;

import org.junit.Test;

import static org.junit.Assert.*;

public class EngineTest {

    //Test return name correctly
    @Test
    public void testGetName() throws Exception {
        Engine myEngine = new Engine(Engine.EngineType.STEAM);
        String name = myEngine.getName();
        assertSame("Steam Engine", name);
    }

    //Test return speed correctly
    @Test
    public void testGetSpeed() throws Exception {
        Engine myEngine = new Engine(Engine.EngineType.DIESEL);
        int Speed = myEngine.getSpeed();
        int expected = 50;
        assertSame(expected,Speed);
    }

}