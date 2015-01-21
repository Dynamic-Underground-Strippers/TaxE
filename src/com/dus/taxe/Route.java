package com.dus.taxe;

import java.util.ArrayList;

/**
 * Representation of a route that a {@link com.dus.taxe.Train} will follow
 */
public class Route {
	private int distanceAlongConnection;
	private int indexOfCurrentNode;
	private int length = 0; //Length of the route as a unit measurement
	private ArrayList<Node> listOfNodes;

	public Route(ArrayList<Node> newNodes) {
		setRoute(newNodes);
		this.indexOfCurrentNode = 0;
		this.distanceAlongConnection = 0;
		this.length = findTotalDistance();
	}

	/**
	 * @return the total distance that this {@link com.dus.taxe.Route} covers
	 */
	private int findTotalDistance() {
		// Finds the total length of the route
		int totalDistance = 0;
		for (int i = 0; i < (listOfNodes.size() - 1); i++) {
			totalDistance += Game.currentMap
					.findDistance(listOfNodes.get(i), listOfNodes.get(i + 1));
		}
		return totalDistance;
	}

	/**
	 * @return the {@link com.dus.taxe.Node} that the {@link com.dus.taxe.Train} is currently at
	 * on this {@link com.dus.taxe.Route}
	 */
	public Node getCurrentNode() {
		return listOfNodes.get(indexOfCurrentNode);
	}

	/**
	 * @return whether or not this {@link com.dus.taxe.Route} has been completed
	 */
	public boolean isComplete() {
		if (this.indexOfCurrentNode == listOfNodes.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the {@link com.dus.taxe.Node}s associated with this {@link com.dus.taxe.Route}
	 * @param newNodes the {@link com.dus.taxe.Node}s to associate with this {@link com.dus.taxe.Route}
	 */
	private void setRoute(ArrayList<Node> newNodes) {
		// Sets the listOfNodes to be equal to the list passed in the constructor
		listOfNodes = newNodes;
	}

	public String toString() {
		// Represents the route as a string
		return listOfNodes.toString();
	}

	/**
	 * Updates the distance that the {@link com.dus.taxe.Train} has travelled along the current
	 * {@link com.dus.taxe.Connection}
	 * @param speed the speed at which the {@link com.dus.taxe.Train} is currently moving at
	 */
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

	/**
	 * Recursively updates the distance along the current {@link com.dus.taxe.Connection}
	 * @param left
	 */
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

	/**
	 * Teleports the {@link com.dus.taxe.Train} to the last {@link com.dus.taxe.Node} in this
	 * {@link com.dus.taxe.Route}
	 */
	public void useTeleport() {
		indexOfCurrentNode = listOfNodes.size()-1;
	}

}
