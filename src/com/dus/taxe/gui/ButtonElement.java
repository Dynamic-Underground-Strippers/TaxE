package com.dus.taxe.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ButtonElement extends GuiElement {
    private Runnable action;
    private Image image;

    public ButtonElement(Rect bounds, String imageName, Runnable action) {
        super(bounds);
        this.action = action;
        if (imageName != null) {
            //noinspection ConstantConditions
            image = new ImageIcon(getClass().getClassLoader().getResource(imageName)).getImage();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (image != null) {
            graphics.drawImage(image, (int) bounds.x, (int) bounds.y, (int) bounds.width,
                    (int) bounds.height, GUI.self);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void onClick(MouseEvent e) {
        if (action != null) action.run();
    }

    @Override
    public void onMouseDown(MouseEvent e) {

    }

    @Override
    public void onMouseUp(MouseEvent e) {

    }
}
