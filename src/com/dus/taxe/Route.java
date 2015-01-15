package com.dus.taxe;

import java.util.ArrayList;
//How do we store where the train is on the route??? indexOfCurrentNode
//Need update route method to occur at the end of every turn. Teleport upgrade
//Change route method, passed an index and a list of nodes and replaces all nodes after that index with those from the ordered list
public class Route{
    ArrayList<Node> listOfNodes;
    int length = 0; //Length of the route as a unit measurement
    private int distanceAlongConnection;
    private int indexOfCurrentNode;
    private Train train;

    public Route(ArrayList<Node> newNodes){
        setRoute(newNodes);
        this.indexOfCurrentNode = 0;
        this.distanceAlongConnection = 0;
      /*  if (!isValid()){
            throw new Error("Invalid Route");
        }*/
        this.length = findTotalDistance();
    }


    public int getCurrentNode(){
        return this.indexOfCurrentNode;
    }

    public void incrementIndexOfCurrentNode(){
        this.indexOfCurrentNode+=1;
    }

    public int getDistanceAlongConnection(){
        return this.distanceAlongConnection;
    }

    public void setDistanceAlongConnection(int distance){
        this.distanceAlongConnection = distance;
    }

    public void updateDistanceAlongConnection(){
        //calculate the length of the connection
        int totalDistance = Game.currentMap.findDistance(this.listOfNodes.get(this.indexOfCurrentNode), this.listOfNodes.get(this.indexOfCurrentNode+1));
        //calculate train's speed
        int distancePerTurn = train.getSpeed()/60; //for one minute turns
        //if the distance travelled is less than then the total, just update the variable
        if (this.distanceAlongConnection + distancePerTurn < totalDistance){
            this.distanceAlongConnection+=distancePerTurn;
            return;
        }else{
            //else move to the next node
            //if there is one
            if(indexOfCurrentNode < listOfNodes.size()-1){
                this.indexOfCurrentNode+=1;
            }else{
                return;
            }
            //calculate distance left to travel
            int leftDistance = distancePerTurn - (totalDistance - distanceAlongConnection);
            //as we are on a new connection the travelled distance is 0
            this.distanceAlongConnection = 0;
            //we need to check if we can travel more.
            //If we can't we are done.
            if (leftDistance == 0){
                return;
            }else{
                //else we repeat
                updateDistanceAlongConnection(leftDistance);
            }
        }
    }
    //update the distance travelled along the current connection (recursive version for distances that cover more than one node)
    //left is the distance (calculated in a previous call) that need to be travelled.
    public void updateDistanceAlongConnection(int left){
        //calculate the length of the connection
        int totalDistance = Game.currentMap.findDistance(this.listOfNodes.get(this.indexOfCurrentNode), this.listOfNodes.get(this.indexOfCurrentNode+1));
        //if the already travelled distance plus the just travelled distance is less than the total distance
        //the updated travelled distance will be just the the sum of the two
        if (this.distanceAlongConnection + left < totalDistance){
            this.distanceAlongConnection+=left;
            return;
        }else{
            //else the train will move to the next node in the route (but only if there one!)
            if(indexOfCurrentNode < listOfNodes.size()-1){
                this.indexOfCurrentNode+=1;
            }else{
                return;
            }

            int leftDistance = left - (totalDistance-distanceAlongConnection);
            //as we are in the next node (i.e. new connection) the distance will now be zero
            this.distanceAlongConnection = 0;
            //we need to check if we can travel more.
            //if we can't we are done
            if (leftDistance == 0){
                return;
            //if we need to travel more we can just repeat the process
            }else{
                updateDistanceAlongConnection(leftDistance);
            }
        }
    }

    public boolean isComplete(){
        if(this.indexOfCurrentNode == listOfNodes.size()-1){
            return true;
        }else{
            return false;
        }
    }

    public void changeRoute(int index, ArrayList<Node> nodes){
        this.listOfNodes.addAll(index,nodes); //assuming that the GUI will force the user to select suitable nodes!!
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
        // Finds the total length of the route
        // initialize variable
        int totalDistance = 0;
        //adds the lenght of every connection between nodes in the route
        for (int i = 0; i < (listOfNodes.size() - 1); i++) {
           totalDistance += Game.currentMap.findDistance(listOfNodes.get(i),listOfNodes.get(i+1));
        }
        return totalDistance;
    }

    public String toString(){
        // Represents the route as a string
        String retStr = "";
        for (int i = 0; i < (listOfNodes.size()); i++) {
           retStr = retStr + listOfNodes.get(i).toString() + " ";
        }
        return retStr;
    }

}
