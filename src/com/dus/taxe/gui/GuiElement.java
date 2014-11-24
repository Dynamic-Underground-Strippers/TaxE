package com.dus.taxe.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GuiElement extends Rectangle {
    private boolean mouseHover = false;

    public GuiElement(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void draw(Graphics graphics);

    final void mouseMoved(MouseEvent e) {
        if (contains(e.getPoint())) {
            if (!mouseHover) {
                mouseOver();
            }
            mouseHover = true;
        } else {
            if (mouseHover) {
                mouseOut();
            }
            mouseHover = false;
        }
    }

    protected void mouseOver() {}

    protected void mouseOut() {}
}
