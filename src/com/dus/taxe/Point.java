package com.dus.taxe;

public class Point {
	private final float x;
	private final float y;

	private Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point(String str) {
		String[] splitStr = str.split(",");
		this.x = Float.parseFloat(splitStr[0].substring(1));
		this.y = Float.parseFloat(splitStr[1].substring(0, splitStr[1].length() - 1));
	}

	/**
	 * Gets the middle point of 2 {@link com.dus.taxe.Point}s
	 *
	 * @param a the first {@link com.dus.taxe.Point}
	 * @param b the second {@link com.dus.taxe.Point}
	 * @return a {@link com.dus.taxe.Point} that is in the middle of the 2 given {@link com.dus
	 * .taxe.Point}s
	 */
	public static Point middle(Point a, Point b) {
		return new Point((a.x + b.x) / 2f, (a.y + b.y) / 2f);
	}

	/**
	 * @return this {@link com.dus.taxe.Point}'s x coordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return this {@link com.dus.taxe.Point}'s y coordinate
	 */
	public float getY() {
		return y;
	}

	public String toString() {
		String s = "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
		return s;
	}
}
