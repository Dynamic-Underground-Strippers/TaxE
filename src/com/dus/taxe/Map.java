package com.dus.taxe;

import java.util.ArrayList;

public class Map {
    public ArrayList<Node> listOfNodes;
    Connection[][] connections;



    public ArrayList<Node> findAdjacentNodes(Node Node1){
        ArrayList<Node> adjacentNodes = new ArrayList<Node>();
        for (int i=0; i<listOfNodes.size(); i++) {
            if (connections[Node1.getId()][i] != null) {
                adjacentNodes.add(listOfNodes.get(i));

            }
        }
        return adjacentNodes;
    }


}
