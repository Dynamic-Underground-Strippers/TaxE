package com.dus.taxe;

import java.util.ArrayList;

public class Map {
    public ArrayList<Node> listOfNodes;
    Connection[][] connections;



    public ArrayList<Node> findAdjacentNodes(Node Node1){
        // returns a list of all nodes that have a connection with the node passed to the function
        ArrayList<Node> adjacentNodes = new ArrayList<Node>();
        for (int i=0; i<listOfNodes.size(); i++) {
            if (connections[Node1.getId()][i] != null) {
                adjacentNodes.add(listOfNodes.get(i));

            }
        }
        return adjacentNodes;
    }

    public int findDistance(Node Node1,Node Node2){
        // returns the distance between two nodes if a connection exists, if it doesn't returns 0
        return 0;
    }

    public boolean checkObstruction(Node Node1,Node Node2){
        // returns whether or not an obstruction exists between two nodes, true is so, false if no obstruction exists.
        return true;
    }
}
