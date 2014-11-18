package com.dus.taxe;

import java.util.ArrayList;

public class Map {
    Connection[][] connections;

    public ArrayList<Node> findAdjacentNodes(Node Node1){
        ArrayList<Node> adjacentNodes = new ArrayList<Node>();
        adjacentNodes.add(Node1);
        return adjacentNodes;
    }
}
