package com.dus.taxe.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class GuiElement {
	Rect bounds = new Rect();
	private boolean animationRunning;
	private Rect animationTargetBounds;
	private float animationSpeed;

	public GuiElement(Rect bounds) {
		this.bounds = bounds;
	}

	public abstract void click(MouseEvent e);

	public abstract void draw(Graphics graphics);

	public abstract void mouseMoved(MouseEvent e);

	public void lerpBounds(Rect targetBounds, float speed) {
		animationRunning = true;
		animationTargetBounds = targetBounds;
		animationSpeed = speed;
	}

	public boolean isAnimationRunning() {
		return animationRunning;
	}

	public final void update() {
		if (animationRunning) {
			bounds.x += (animationTargetBounds.x - bounds.x) * animationSpeed;
			bounds.y += (animationTargetBounds.y - bounds.y) * animationSpeed;
			bounds.width += (animationTargetBounds.width - bounds.width) * animationSpeed;
			bounds.height += (animationTargetBounds.height - bounds.height) * animationSpeed;
			GUI.self.repaint();
			if (Math.abs(bounds.x - animationTargetBounds.x) < 1 &&
					Math.abs(bounds.y - animationTargetBounds.y) < 1 &&
					Math.abs(bounds.width - animationTargetBounds.width) < 1 &&
					Math.abs(bounds.height - animationTargetBounds.height) < 1) {
				animationRunning = false;
				bounds = animationTargetBounds;
				return;
			}
		}
	}
}
