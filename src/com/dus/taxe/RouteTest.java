package com.dus.taxe;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;


public class RouteTest extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Map map = new Map();

    Game game = new Game(map);

    Train train = new Train();

    public ArrayList<Node> listOfNodes;

    public ArrayList<Node> createList(){
        ArrayList<Node> arr = new ArrayList<Node>();

        ArrayList<Node> a;

        arr.add(0,map.retrieveNode(1));
        a = map.findAdjacentNodes(arr.get(0));
        arr.add(1,a.get(0));
        a = map.findAdjacentNodes(arr.get(1));
        arr.add(2,a.get(0));
        a = map.findAdjacentNodes(arr.get(2));
        arr.add(3,a.get(0));
        a = map.findAdjacentNodes(arr.get(3));
        arr.add(4,a.get(0));
        a = map.findAdjacentNodes(arr.get(4));
        arr.add(5,a.get(0));
        return arr;
    }

    @Test
    public void testRoute() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        assertEquals(route.getDistanceAlongConnection(), 0);
        assertEquals(route.getCurrentNode(), 0);
    }

    @Test
    public void testLessDistance() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        route.updateDistanceAlongConnection();
        assertEquals(route.getDistanceAlongConnection(), (15/60));
    }

    @Test
    public void testMoreDistance() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        route.updateDistanceAlongConnection(distance + 1);
        assertEquals(route.getDistanceAlongConnection(), 1);
        assertEquals(route.getCurrentNode(),1);
    }

    public void testExactDistance() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        route.updateDistanceAlongConnection(distance);
        assertEquals(route.getDistanceAlongConnection(), 0);
        assertEquals(route.getCurrentNode(),1);
    }

    public void test2NodeDistance() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        distance = distance + map.findDistance(route.listOfNodes.get(route.getCurrentNode()+1), route.listOfNodes.get(route.getCurrentNode()+2));

        route.updateDistanceAlongConnection(distance);
        assertEquals(route.getDistanceAlongConnection(), 0);
        assertEquals(route.getCurrentNode(),2);
    }

    public void test2NodeDistancePlus1() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        distance = distance + map.findDistance(route.listOfNodes.get(route.getCurrentNode()+1), route.listOfNodes.get(route.getCurrentNode()+2));
        route.updateDistanceAlongConnection(distance + 1);
        assertEquals(route.getDistanceAlongConnection(), 1);
        assertEquals(route.getCurrentNode(),2);
    }

    public void test2NodeDistanceMinus1() throws Exception {
        Route route = new Route(createList());
        route.setTrain(new Train());
        int distance = map.findDistance(route.listOfNodes.get(route.getCurrentNode()), route.listOfNodes.get(route.getCurrentNode()+1));
        distance = distance + map.findDistance(route.listOfNodes.get(route.getCurrentNode()+1), route.listOfNodes.get(route.getCurrentNode()+2));
        route.updateDistanceAlongConnection(distance - 1);
        assertEquals(route.getDistanceAlongConnection(), map.findDistance(route.listOfNodes.get(route.getCurrentNode()+1), route.listOfNodes.get(route.getCurrentNode()+2))-1);
        assertEquals(route.getCurrentNode(),1);
    }
}
