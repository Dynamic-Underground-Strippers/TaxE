package com.dus.taxe;

import java.util.ArrayList;

public class Route {
    ArrayList<Node> listOfNodes;
    int length = 0;

    public Route(ArrayList<Node> newNodes){
        setRoute(newNodes);
    }
/*    public boolean isBlocked() {
        // Returns whether or not the route is blocked by an obstacle
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
            if (Game.currentMap.checkObstruction(listOfNodes.get(i),listOfNodes.get(i+1))) {
                return true;
            }
        }
        return false;
    }*/
    private boolean isValid(){
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
            if ((Game.currentMap.findDistance(listOfNodes.get(i),listOfNodes.get(i+1)))==0){
                return false;
            }
        }
        return true;
    }
    private void setRoute(ArrayList<Node> newNodes){
        listOfNodes = newNodes;
    }

    public int findTotalDistance(){
        int totalDistance = 0;
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
           totalDistance += Game.currentMap.findDistance(listOfNodes.get(i),listOfNodes.get(i+1));
        }
        return totalDistance;
    }


}
