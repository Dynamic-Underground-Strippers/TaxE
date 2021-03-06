package com.dus.taxe.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * {@link com.dus.taxe.gui.GuiElement} that represents a solid block of colour on the screen
 */
class SolidColourRect extends GuiElement {
	private final Color colour;

	SolidColourRect(Rect bounds, Color colour) {
		super(bounds);
		this.colour = colour;
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(colour);
		graphics.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void onClick(MouseEvent e) {

	}

	@Override
	public void onMouseDown(MouseEvent e) {

	}

	@Override
	public void onMouseUp(MouseEvent e) {

	}
}