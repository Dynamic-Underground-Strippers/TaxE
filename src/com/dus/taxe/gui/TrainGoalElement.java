package com.dus.taxe.gui;

import com.dus.taxe.Goal;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class TrainGoalElement extends GuiElement {
    private Goal goal;
    private Image image;

    public TrainGoalElement(Rect bounds) {
        super(bounds);
    }

    @Override
    public void click(MouseEvent e) {
        if (!isAnimationRunning()) {
            if (bounds.x == -600) {
                lerpBounds(new Rect(0, bounds.y, bounds.width, bounds.height), 0.075f);
            } else {
                lerpBounds(new Rect(-600, bounds.y, bounds.width, bounds.height), 0.075f);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, (int) bounds.x, (int) bounds.y, (int) bounds.width,
                    (int) bounds.height, GUI.self);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GUI.self.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
        if (goal == null || goal.getCurrentTrain() == null) {
            image = null;
        } else {
            switch (goal.getCurrentTrain().getEngine().getType()) {
                case HAND_CART:
                    image = new ImageIcon(getClass().getClassLoader().getResource("electric_side.png")).getImage();

                    break;
                case STEAM:
                    image = new ImageIcon(getClass().getClassLoader().getResource("steam_side.png")).getImage();
                    break;
                case DIESEL:
                    image = new ImageIcon(getClass().getClassLoader().getResource("diesel_side.png")).getImage();
                    break;
                case ELECTRIC:
                    image = new ImageIcon(getClass().getClassLoader().getResource("electric_side.png")).getImage();
                    break;
                case ROCKET:
                    image = new ImageIcon(getClass().getClassLoader().getResource("electric_side.png")).getImage();
                    break;
            }
        }
    }
}
