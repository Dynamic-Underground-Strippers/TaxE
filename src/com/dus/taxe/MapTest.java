package com.dus.taxe;

import junit.framework.TestCase;
import org.junit.Test;

public class MapTest extends TestCase {
    @Test
    public void testFindAdjNodes(){

        Map newMap = new Map();
        assertEquals(newMap.listOfNodes.get(11).getName(), "Milan");
        assertEquals(newMap.listOfNodes.get(9).getName(), "Antwerp");

        assertEquals(newMap.findAdjacentNodes(newMap.listOfNodes.get(11)).size(),5);


    }
    @Test
    public void testFindDist(){
       Map newMap = new Map();
       assertEquals(newMap.findDistance(newMap.listOfNodes.get(11), newMap.listOfNodes.get(9)), 290);
    }

    @Test
    public void testGetRandGoal(){
        Map newMap = new Map();
        Goal g1 = newMap.getRandomGoal();
        Goal g2 = newMap.getRandomGoal();
        Goal g3 = newMap.getRandomGoal();
        System.out.println(g3.getDescription());
        System.out.println(g2.getDescription());
        System.out.println(g1.getDescription());

    }
}