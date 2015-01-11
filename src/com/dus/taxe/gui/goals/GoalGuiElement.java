package com.dus.taxe.gui.goals;

import com.dus.taxe.Goal;
import com.dus.taxe.gui.GUI;
import com.dus.taxe.gui.GuiElement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GoalGuiElement extends GuiElement {
	private Color colour;
	private Goal goal;
	private boolean focus = false;
	private int originalX;
	private int originalWidth;
	Image img;

	GoalGuiElement(int x, int y, int width, int height) {
		super(x, y, width, height);
		originalX = x;
		originalWidth = width;
		img = new ImageIcon(getClass().getClassLoader().getResource("steam_train.png")).getImage();
	}

	@Override
	public void draw(Graphics graphics) {
		updateBounds();
		graphics.drawImage(img, (int) x, (int) y, (int) width, (int) height, GUI.self);
//		graphics.setColor(colour);
//		graphics.fillRect((int) (getParent().x + x), (int) (getParent().y + y), (int) width, (int) height);
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

	public Color getColor() {
		return colour;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Override
	protected void onMouseOver() {
		if (((GoalContainer) getParent()).getActiveChild() == null) {
			focus = true;
			setZIndex(1);
			((GoalContainer) getParent()).setActiveChild(this);
		}
	}

	@Override
	protected void onMouseOut() {
		focus = false;
		((GoalContainer) getParent()).setActiveChild(null);
	}

	public boolean hasFocus() {
		return focus;
	}
}
