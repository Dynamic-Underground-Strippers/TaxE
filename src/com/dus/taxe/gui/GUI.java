package com.dus.taxe.gui;

import com.dus.taxe.Goal;
import com.dus.taxe.gui.goals.GoalContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    Image mapImage;
    ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();

    public GUI() {
        //noinspection ConstantConditions
        mapImage = new ImageIcon(getClass().getClassLoader().getResource("map.png")).getImage();
        GoalContainer gc = new GoalContainer(50, 50, 1000, 150);
        gc.setGoals(new Goal[]{new Goal("Goal 1", "Description 1", 100, null, null), new Goal("Goal 2", "Description 2", 200, null, null), new Goal("Goal 2", "Description 2", 300, null, null)});
        addGuiElement(gc);
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
                for (GuiElement guiElement : guiElements) {
                    guiElement.mouseMoved(e);
                }
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        int imageWidth = (int) ((float) mapImage.getWidth(this) * ((float) getHeight() / (float) mapImage.getHeight(this)));
        graphics.drawImage(mapImage, getWidth() - imageWidth, 0, imageWidth, getHeight(), this);
        for (GuiElement guiElement : guiElements) {
            guiElement.draw(graphics);
        }
    }

    public void addGuiElement(GuiElement guiElement) {
        guiElements.add(guiElement);
        repaint();
    }
}
