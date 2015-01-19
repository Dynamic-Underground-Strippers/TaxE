package com.dus.taxe;

public class Goal {
	private String description;
	private Node end;
	private int points;
	private Node start;

	public Goal(int points, Node start, Node end) {
		this.points = points;
		this.start = start;
		this.end = end;
		this.description =
				"Move a train from " + this.start.getName() + " to " + this.end.getName();
	}

	public boolean equals(Object other) {
		if (other instanceof Goal) {
			Goal g = (Goal) other;
			return start.equals(g.start) && end.equals(g.end) && points == g.points;
		} else {
			return false;
		}
	}

	public String getDescription() {
		return description;
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
}

