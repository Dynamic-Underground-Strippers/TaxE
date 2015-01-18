package com.dus.taxe;

public class Goal {
	private String description;
	private Node end;
	private int points;
	private Node start;
	private Train currentTrain;

	public Goal(int points, Node start, Node end) {
		this.points = points;
		this.start = start;
		this.end = end;
		this.description =
				"Move a train from " + this.start.getName() + " to " + this.end.getName();
		this.currentTrain = null;
	}

	public String getDescription() {
		return description;
	}

	public void setCurrentTrain(Train currentTrain) {
		this.currentTrain = currentTrain;
	}

	public Train getCurrentTrain() {
		return this.currentTrain;
	}

	public Node getEnd() {
		return end;
	}

	public int getPoints() {
		return points;
	}

	public Node getStart() {
		return start;
	}

	public boolean isComplete() {
		return currentTrain.getRoute().isComplete();
	}
}

