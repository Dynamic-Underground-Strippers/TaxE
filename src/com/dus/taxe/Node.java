package com.dus.taxe;

/**
 * Base class for all stations, junctions etc on the map
 */
public abstract class Node {
	private final int id;
	private final Point location;
	private final String name;

	Node(int id, String name, Point location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public boolean equals(Object other) {
		if (other instanceof Node) {
			Node n = (Node) other;
			return n.id == id;
		} else {
			return false;
		}
	}

	/**
	 * @return the ID of this {@link com.dus.taxe.Node}
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the location of this {@link com.dus.taxe.Node}
	 */
	public Point getLocation() {
		return location;
	}


	/**
	 * @return the ID of this {@link com.dus.taxe.Node}
	 */
	public String getName() {
		return name;
	}

	public String toString() {
		return Integer.toString(id) + "," + location.toString() + "," + name;
	}
}

