package com.dus.taxe.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class GuiElement {
	Rect bounds = new Rect();
	Rect srcBounds = new Rect();
	private String tooltip;
	private boolean hovering = false;

	public GuiElement(Rect bounds) {
		this(bounds, bounds);
	}

	public GuiElement(Rect bounds, Rect srcBounds) {
		this.bounds = bounds;
		this.srcBounds = srcBounds;
	}

	public abstract void click(MouseEvent e);

	public abstract void draw(Graphics2D graphics);

	void drawTooltip(Graphics2D graphics) {
		if (tooltip != null && hovering) {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fillRect((int) (bounds.x + bounds.width + 10),
					(int) (bounds.y + (bounds.height / 2f) -
							((graphics.getFontMetrics().getHeight() * 1.2f) / 2f)),
					graphics.getFontMetrics().stringWidth(tooltip) + 20,
					(int) (graphics.getFontMetrics().getHeight() * 1.2f));
			graphics.setColor(Color.white);
			graphics.drawString(tooltip, bounds.x + bounds.width + 10 + 10,
					bounds.y + graphics.getFontMetrics().getHeight() * 1.1f);
		}
	}

	public String getTooltip() {
		return tooltip;
	}

	public abstract void mouseMoved(MouseEvent e);

	public void mouseMovedExternal(MouseEvent e) {
		hovering = false;
	}

	void mouseMovedInternal(MouseEvent e) {
		hovering = true;
		mouseMoved(e);
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void slerpBounds(Rect targetBounds, float speed) {
		bounds.animationRunning = true;
		bounds.animationTargetBounds = targetBounds;
		bounds.animationSpeed = speed;
	}

	public void slerpSrcBounds(Rect targetBounds, float speed) {
		srcBounds.animationRunning = true;
		srcBounds.animationTargetBounds = targetBounds;
		srcBounds.animationSpeed = speed;
	}

	public boolean isAnimationRunning() {
		return bounds.animationRunning || srcBounds.animationRunning;
	}

	public final void update() {
		bounds.update();
		srcBounds.update();
	}
}
