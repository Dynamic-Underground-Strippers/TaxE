package com.dus.taxe.gui;

import java.awt.Point;

/**
 * Utility class that represents a rectangle with floating-point precision
 */
class Rect {
	float height;
	float width;
	float x;
	float y;
	boolean animationRunning;
	float animationSpeed;
	Rect animationTargetBounds;

	Rect() {
		this(0, 0, 0, 0);
	}

	Rect(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Determines whether or not the {@link com.dus.taxe.gui.Rect} contains the given coordinates
	 * @param x
	 * @param y
	 * @return whether or not the {@link com.dus.taxe.gui.Rect} contains the given coordinates
	 */
	boolean contains(float x, float y) {
		return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
	}

	/**
	 * Determines whether or not the {@link com.dus.taxe.gui.Rect} contains the given coordinates
	 * @param p
	 * @return whether or not the {@link com.dus.taxe.gui.Rect} contains the coordinates derived
	 * from the given {@link java.awt.Point}
	 */
	boolean contains(Point p) {
		return contains(p.x, p.y);
	}

	/**
	 * Determines whether or not the {@link com.dus.taxe.gui.Rect} contains the given coordinates
	 * @param p
	 * @return whether or not the {@link com.dus.taxe.gui.Rect} contains the coordinates derived
	 * from the given {@link com.dus.taxe.Point}
	 */
	boolean contains(com.dus.taxe.Point p) {
		return contains(p.getX(), p.getY());
	}

	public boolean equals(Object other) {
		if (other instanceof Rect) {
			Rect r = (Rect) other;
			return x == r.x && y == r.y && width == r.width && height == r.height;
		} else {
			return false;
		}
	}

	public String toString() {
		return "Rect(x:" + x + ", y:" + y + ", width:" + width + ", height:" + height + ")";
	}

	/**
	 * Updates the properties of the {@link com.dus.taxe.gui.Rect}, if they are being animated
	 */
	final void update() {
		if (animationRunning) {
			x += (animationTargetBounds.x - x) * animationSpeed * (GUI.frameTime / 32f);
			y += (animationTargetBounds.y - y) * animationSpeed * (GUI.frameTime / 32f);
			width += (animationTargetBounds.width - width) * animationSpeed * (GUI.frameTime / 32f);
			height += (animationTargetBounds.height - height) * animationSpeed *
					(GUI.frameTime / 32f);
			if (Math.abs(x - animationTargetBounds.x) < 1 &&
					Math.abs(y - animationTargetBounds.y) < 1 &&
					Math.abs(width - animationTargetBounds.width) < 1 &&
					Math.abs(height - animationTargetBounds.height) < 1) {
				animationRunning = false;
				x = animationTargetBounds.x;
				y = animationTargetBounds.y;
				width = animationTargetBounds.width;
				height = animationTargetBounds.height;
			}
		}
	}
}
