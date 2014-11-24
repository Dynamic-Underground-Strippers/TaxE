package com.dus.taxe.gui.goals;

import com.dus.taxe.Goal;
import com.dus.taxe.gui.GuiElement;

import java.awt.*;

public class GoalContainer extends GuiElement {
    private Goal[] goals = new Goal[3];
    private final Color[] colours = {Color.RED, Color.YELLOW, Color.GREEN};

    public GoalContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setGoals(Goal[] goals) {
        this.goals = goals;
    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < goals.length; i++) {
            graphics.setColor(colours[i]);
            graphics.fillRect(x + (int) (i * ((float) width / 3)), y, (int) ((float) width / 3f), height);
        }
    }
}