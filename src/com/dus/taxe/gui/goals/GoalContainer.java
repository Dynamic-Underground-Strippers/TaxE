package com.dus.taxe.gui.goals;

import com.dus.taxe.Goal;
import com.dus.taxe.gui.GuiElement;

import java.awt.*;

public class GoalContainer extends GuiElement {
	private GoalGuiElement activeChild;
	private GoalGuiElement[] goalGuiElements = new GoalGuiElement[3];
	private final Color[] colours = {Color.RED, Color.YELLOW, Color.GREEN};

	public GoalContainer(int x, int y, int width, int height) {
		super(x, y, width, height);
		for (int i = 0; i < goalGuiElements.length; i++) {
			goalGuiElements[i] = new GoalGuiElement(x + (int) (i * ((float) width / 3)), y, (int) ((float) width / 3f), height);
			goalGuiElements[i].setColour(colours[i]);
			addChild(goalGuiElements[i]);
		}
	}

	public GoalGuiElement getActiveChild() {
		return activeChild;
	}

	public void setActiveChild(GoalGuiElement activeChild) {
		this.activeChild = activeChild;
	}

	public void setGoals(Goal[] goals) {
		for (int i = 0; i < goalGuiElements.length; i++) {
			goalGuiElements[i].setGoal(goals[i]);
		}
	}

//	public void draw(Graphics g) {
//		for (GoalGuiElement gge : goalGuiElements) {
//			if (gge == activeChild) {
//				gge.setColour(Color);
//			}
//		}
//		super.draw(g);
//		for (Color c : colours) {
//			if (activeChild.getColor().equals(c)) {
//				g.drawString("" + activeChild.getZIndex(), 50, 50);
//			}
//		}
//	}
}