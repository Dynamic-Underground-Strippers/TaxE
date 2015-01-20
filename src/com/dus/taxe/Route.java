package com.dus.taxe;

import java.util.ArrayList;

//How do we store where the train is on the route??? indexOfCurrentNode
//Need update route method to occur at the end of every turn. Teleport upgrade
//Change route method, passed an index and a list of nodes and replaces all nodes after that index with those from the ordered list
public class Route {
	int length = 0; //Length of the route as a unit measurement
	ArrayList<Node> listOfNodes;
	private int distanceAlongConnection;
	private int indexOfCurrentNode;

	public Route(ArrayList<Node> newNodes) {
		setRoute(newNodes);
		this.indexOfCurrentNode = 0;
		this.distanceAlongConnection = 0;
	  /*  if (!isValid()){
			throw new Error("Invalid Route");
        }*/
		this.length = findTotalDistance();
	}

	public void changeRoute(int index, ArrayList<Node> nodes) {
		this.listOfNodes.addAll(index,
				nodes); //assuming that the GUI will force the user to select suitable nodes!!
	}

	private int findTotalDistance() {
		// Finds the total length of the route
		int totalDistance = 0;
		for (int i = 0; i < (listOfNodes.size() - 1); i++) {
			totalDistance += Game.currentMap
					.findDistance(listOfNodes.get(i), listOfNodes.get(i + 1));
		}
		return totalDistance;
	}

	public int getCurrentNode() {
		return this.indexOfCurrentNode;
	}

	public int getDistanceAlongConnection() {
		return this.distanceAlongConnection;
	}

	public void setDistanceAlongConnection(int distance) {
		this.distanceAlongConnection = distance;
	}

	public void incrementIndexOfCurrentNode() {
		this.indexOfCurrentNode += 1;
	}

	public boolean isComplete() {
		if (this.indexOfCurrentNode == listOfNodes.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid() {
		// Checks whether a route is possible, should not be necessary as this should all be handled within the GUI
		for (int i = 0; i < (listOfNodes.size() - 1); i++) {
			if ((Game.currentMap.findDistance(listOfNodes.get(i), listOfNodes.get(i + 1))) == 0) {
				return false;
			}
		}
		return true;
	}


	private void setRoute(ArrayList<Node> newNodes) {
		// Sets the listOfNodes to be equal to the list passed in the constructor
		listOfNodes = newNodes;
		System.out.println(listOfNodes.size());
	}

	public String toString() {
		// Represents the route as a string
		String retStr = "";
		for (int i = 0; i < (listOfNodes.size()); i++) {
			retStr = retStr + listOfNodes.get(i).toString() + " ";
		}
		return retStr;
	}

<<<<<<< HEAD
	public void updateDistanceAlongConnection() {
		System.out.println(listOfNodes.size());
		int totalDistance = Game.currentMap
				.findDistance(this.listOfNodes.get(this.indexOfCurrentNode),
						this.listOfNodes.get(this.indexOfCurrentNode + 1));
		int speedPerTurn = train.getSpeed();
		if (this.distanceAlongConnection + speedPerTurn < totalDistance) {
			this.distanceAlongConnection += speedPerTurn;
		} else {
			if (indexOfCurrentNode < listOfNodes.size() - 1) {
				this.indexOfCurrentNode += 1;
			} else {
				return;
			}
			this.distanceAlongConnection = 0;
			int leftDistance = distanceAlongConnection + speedPerTurn - totalDistance;
			if (leftDistance == 0) {
				return;
			} else {
				updateDistanceAlongConnection(leftDistance);
=======
	public void updateDistanceAlongConnection(int speed) {
		if (Game.currentMap.getConnections()[listOfNodes.get(indexOfCurrentNode).getId()][listOfNodes.get(indexOfCurrentNode+1).getId()]
				.getDistance()-distanceAlongConnection>speed){
			distanceAlongConnection = Game.currentMap.getConnections()[listOfNodes.get(indexOfCurrentNode).getId()][listOfNodes.get(indexOfCurrentNode+1).getId()]
					.getDistance()-distanceAlongConnection-speed;
		}else{
			int remainingSpeed;
			remainingSpeed = speed - (Game.currentMap.getConnections()[listOfNodes.get(indexOfCurrentNode).getId()][listOfNodes.get(indexOfCurrentNode+1).getId()]
					.getDistance()-distanceAlongConnection);
			indexOfCurrentNode++;
			while ((remainingSpeed>Game.currentMap.getConnections()[listOfNodes.get(indexOfCurrentNode).getId()][listOfNodes.get(indexOfCurrentNode+1).getId()]
					.getDistance()) && indexOfCurrentNode!=listOfNodes.size()){
				remainingSpeed = remainingSpeed - (Game.currentMap.getConnections()[listOfNodes.get(indexOfCurrentNode).getId()][listOfNodes.get(indexOfCurrentNode+1).getId()].getDistance());
>>>>>>> game-methods
			}
			distanceAlongConnection=remainingSpeed;
		}

	}

	public void useTeleport(){
		indexOfCurrentNode = listOfNodes.size();
	}

}
