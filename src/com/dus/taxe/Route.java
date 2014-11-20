package com.dus.taxe;

import java.util.ArrayList;

public class Route{
    ArrayList<Node> listOfNodes;
    int length = 0; //Length of the route as a unit measurement

    public Route(ArrayList<Node> newNodes){
        setRoute(newNodes);
      /*  if (!isValid()){
            throw new Error("Invalid Route");
        }*/
        length = findTotalDistance();
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
        // Checks whether a route is possible, should not be necessary as this should all be handled within the GUI
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
            if ((Game.currentMap.findDistance(listOfNodes.get(i),listOfNodes.get(i+1)))==0){
                return false;
            }
        }
        return true;
    }

    private void setRoute(ArrayList<Node> newNodes){
        // Sets the listOfNodes to be equal to the list passed in the constructor
        listOfNodes = newNodes;
    }

    private int findTotalDistance(){
        int totalDistance = 0;
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
           totalDistance += Game.currentMap.findDistance(listOfNodes.get(i),listOfNodes.get(i+1));
        }
        return totalDistance;
    }

    public String toString(){
        String retStr = "";
        for (int i = 0; i < (listOfNodes.size()); i++) {
           retStr = retStr + listOfNodes.get(i).toString() + " ";
        }
        return retStr;
    }

}
