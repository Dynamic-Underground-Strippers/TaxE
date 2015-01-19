package com.dus.taxe.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class SolidColourRect extends GuiElement {
	private final Color colour;

	public SolidColourRect(Rect bounds, Color colour) {
		super(bounds);
		this.colour = colour;
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

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(colour);
		graphics.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
