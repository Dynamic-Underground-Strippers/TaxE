package com.dus.taxe;

/**
 * Representation of an in-game goal
 */
public class Goal {
	private final String description;
	private final Node end;
	private final Node start;

	public Goal(Node start, Node end) {
		this.start = start;
		this.end = end;
		this.description =
				"Move a train from " + this.start.getName() + " to " + this.end.getName();
	}

	/**
	 * Determines whether or not this {@link com.dus.taxe.Goal} contains the given {@link com.dus.taxe.Node}
	 *
	 * @param node the {@link com.dus.taxe.Node} to check
	 * @return whether or not this {@link com.dus.taxe.Goal} contains the given {@link com.dus
	 * .taxe.Node}
	 */
	public boolean containsNode(Node node) {
		if ((this.start.getId() == node.getId()) || (this.end.getId() == node.getId())) {
			return true;
		}
		return false;
	}

	public boolean equals(Object other) {
		if (other instanceof Goal) {
			Goal g = (Goal) other;
			return start.equals(g.start) && end.equals(g.end);
		} else {
			return false;
		}
	}

	/**
	 * @return a description of the {@link com.dus.taxe.Goal}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the end {@link com.dus.taxe.Node} of the {@link com.dus.taxe.Goal}
	 */
	public Node getEnd() {
		return end;
	}

	/**
	 * @return the start {@link com.dus.taxe.Node} of the {@link com.dus.taxe.Goal}
	 */
	public Node getStart() {
		return start;
	}
}

