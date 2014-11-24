package com.dus.taxe.gui.goals;

import com.dus.taxe.Goal;
import com.dus.taxe.gui.GuiElement;

import java.awt.Color;
import java.awt.Graphics;

public class GoalGuiElement extends GuiElement {
	private Color colour;
	private Goal goal;
	private boolean focus = false;
	private int originalX;
	private int originalWidth;

	GoalGuiElement(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalWidth = width;
	}

	@Override
	public void draw(Graphics graphics) {
		updateBounds();
		graphics.setColor(colour);
		graphics.fillRect((int) (getParent().x + x), (int) (getParent().y + y), (int) width, (int) height);
	}

	private void updateBounds() {
		if (focus) {
			x += (getParent().x - x) * 0.08f;
			width += (getParent().width - width) * 0.08f;
		} else {
			x += (originalX - x) * 0.08f;
			width += (originalWidth - width) * 0.08f;
		}
		if (Math.abs(x - originalX) < 1 && Math.abs(width - originalWidth) < 1) setZIndex(0);
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Override
	protected void onMouseOver() {
		focus = true;
		setZIndex(1);
	}

	@Override
	protected void onMouseOut() {
		focus = false;
	}

	public boolean hasFocus() {
		return focus;
	}

	public void setActiveChild(GuiElement child) {

	}
}
