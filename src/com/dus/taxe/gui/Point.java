package com.dus.taxe.gui;

public class Point {
	private float x;
	private float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point(String str) {
		String[] splitStr = str.split(",");
		this.x = Float.parseFloat(splitStr[0].substring(1));
		this.y = Float.parseFloat(splitStr[1].substring(0, splitStr[1].length() - 1));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String toString() {
		String s = "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
		return s;
	}

	public static float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
