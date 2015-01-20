package com.dus.taxe;

public class Goal {
	private String description;
	private Node end;
	private Node start;

	public Goal(Node start, Node end) {
		this.start = start;
		this.end = end;
		this.description =
				"Move a train from " + this.start.getName() + " to " + this.end.getName();
	}

	public boolean equals(Object other) {
		if (other instanceof Goal) {
			Goal g = (Goal) other;
			return start.equals(g.start) && end.equals(g.end);
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

	public Node getStart() {
		return start;
	}

	public boolean containsNode(Node node) {
		if ((this.start.getId() == node.getId()) || (this.end.getId() == node.getId())) {
			return true;
		}
		return false;
	}
}

