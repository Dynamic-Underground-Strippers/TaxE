package com.dus.taxe;

import java.util.ArrayList;

public class Route {
    ArrayList<Node> nodes;
    int length = 0;

    public Route(ArrayList<Node> newNodes){
        setRoute(newNodes);
    }
/*    public boolean isBlocked() {
        // Returns whether or not the route is blocked by an obstacle
        for (int i = 0; i < (nodes.size() - 1); i++) {
            if (Game.currentMap.checkObstruction(nodes.get(i),nodes.get(i+1))) {
                return true;
            }
        }
        return false;
    }*/

    private void setRoute(ArrayList<Node> newNodes){
        nodes = newNodes;
    }

    public int findTotalDistance(){
        int totalDistance = 0;
        for (int i = 0; i < (nodes.size() - 1); i++) {
           totalDistance += Game.currentMap.findDistance(nodes.get(i),nodes.get(i+1));
        }
        return totalDistance;
    }


}
