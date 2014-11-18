package com.dus.taxe;

import java.util.ArrayList;

public class Route {
    ArrayList<Node> nodes = new ArrayList<Node>();

    public boolean isBlocked() {
        for (int i = 0; i < (nodes.size() - 1); i++) {
            if (Game.currentMap.checkObstruction(nodes.get(i),nodes.get(i+1))) {
                return true;
            }
        }
        return false;
    }

    public int findTotalDistance(){
        int totalDistance = 0;
        for (int i = 0; i < (nodes.size() - 1); i++) {
           totalDistance += Game.currentMap.findDistance(nodes.get(i),nodes.get(i+1));
        }
        return totalDistance;
    }


}
