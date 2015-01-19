package com.dus.taxe;

public abstract class Node {
	private final int id;
	private final Point location;
	private final String name;

	public Node(int id, String name, Point location) {
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

	public int getId() {
		return id;
	}

	public Point getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return Integer.toString(id) + "," + location.toString() + "," + name;
	}
}

