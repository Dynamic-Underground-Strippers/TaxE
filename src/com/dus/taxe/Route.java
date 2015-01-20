package com.dus.taxe;

import java.util.ArrayList;

public class Route {
	private int length = 0; //Length of the route as a unit measurement
	private ArrayList<Node> listOfNodes;
	private int distanceAlongConnection;
	private int indexOfCurrentNode;

	public Route(ArrayList<Node> newNodes) {
		setRoute(newNodes);
		this.indexOfCurrentNode = 0;
		this.distanceAlongConnection = 0;
		this.length = findTotalDistance();
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

	public Node getCurrentNode() {
		return listOfNodes.get(indexOfCurrentNode);
	}

	public boolean isComplete() {
		if (this.indexOfCurrentNode == listOfNodes.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

	private void setRoute(ArrayList<Node> newNodes) {
		// Sets the listOfNodes to be equal to the list passed in the constructor
		listOfNodes = newNodes;
	}

	public String toString() {
		// Represents the route as a string
		return listOfNodes.toString();
	}


	public void updateDistanceAlongConnection(int speed) {
		int totalDistance=0;
		if (indexOfCurrentNode < listOfNodes.size() - 1) {
			totalDistance = Game.currentMap
					.findDistance(this.listOfNodes.get(this.indexOfCurrentNode),
							this.listOfNodes.get(this.indexOfCurrentNode + 1));
		} else {
			return;
		}
		if (this.distanceAlongConnection + speed < totalDistance) {
			this.distanceAlongConnection += speed;
		} else {
			if (indexOfCurrentNode < listOfNodes.size() - 1) {
				this.indexOfCurrentNode += 1;
			} else {
				return;
			}
			this.distanceAlongConnection = 0;
			int leftDistance = distanceAlongConnection + speed - totalDistance;
			if (leftDistance == 0) {
				return;
			} else {
				updateDistanceAlongConnectionRecursive(leftDistance);
			}
		}
	}

	void updateDistanceAlongConnectionRecursive(int left) {
		int totalDistance = 0;
		if (indexOfCurrentNode < listOfNodes.size() - 1) {
			totalDistance = Game.currentMap
					.findDistance(this.listOfNodes.get(this.indexOfCurrentNode),
							this.listOfNodes.get(this.indexOfCurrentNode + 1));
		} else {
			return;
		}

		if (this.distanceAlongConnection + left < totalDistance) {
			this.distanceAlongConnection += left;
		} else {
			if (indexOfCurrentNode < listOfNodes.size() - 1) {
				this.indexOfCurrentNode += 1;
			} else {
				return;
			}
			this.distanceAlongConnection = 0;
			int leftDistance = distanceAlongConnection + left - totalDistance;
			if (leftDistance == 0) {
				return;
			} else {
				updateDistanceAlongConnection(leftDistance);
			}
		}
	}

	public void useTeleport() {
		indexOfCurrentNode = listOfNodes.size() - 1;
	}

}
