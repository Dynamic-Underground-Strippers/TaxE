package com.dus.taxe;

/**
 * Representation of a link between 2 {@link com.dus.taxe.Node}s
 */
public class Connection {
	private final int distance;

	public Connection(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the distance of the {@link com.dus.taxe.Connection}
	 */
	public int getDistance() {
		return distance;
	}

}
