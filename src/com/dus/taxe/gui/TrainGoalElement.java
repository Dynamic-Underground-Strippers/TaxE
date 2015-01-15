package com.dus.taxe.gui;

import com.dus.taxe.Goal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TrainGoalElement extends GuiElement {
    private Goal goal;
    private BufferedImage image;

    public TrainGoalElement(Rect bounds) {
        super(bounds);
    }

    @Override
    public void click(MouseEvent e) {
        if (!isAnimationRunning()) {
            if (bounds.x == -490) {
                slerpBounds(new Rect(0, bounds.y, bounds.width, bounds.height), 0.075f);
            } else {
                slerpBounds(new Rect(-490, bounds.y, bounds.width, bounds.height), 0.075f);
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (image != null) {
//            graphics.drawImage(image.getSubimage((int) srcBounds.x, (int) srcBounds.y, (int) srcBounds.width,
//                    (int) srcBounds.height), (int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height, GUI.self);
            graphics.drawImage(image, (int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height, GUI.self);
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
                    try {
                        image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case STEAM:
                    try {
                        image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case DIESEL:
                    try {
                        image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ELECTRIC:
                    try {
                        image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ROCKET:
                    try {
                        image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
