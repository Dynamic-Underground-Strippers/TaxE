package com.dus.taxe.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

abstract class GuiElement {
    Rect bounds = new Rect();
    private boolean hovering = false;
    private String tooltip;

    GuiElement(Rect bounds) {
        this.bounds = bounds;
    }

    public abstract void draw(Graphics2D graphics);

    void drawTooltip(Graphics2D graphics) {
        if (tooltip != null && hovering) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect((int) (bounds.x + bounds.width + 10),
                    (int) (bounds.y + (bounds.height / 2f) -
                            ((graphics.getFontMetrics().getHeight() * 1.2f) / 2f)),
                    graphics.getFontMetrics().stringWidth(tooltip) + 20,
                    (int) (graphics.getFontMetrics().getHeight() * 1.2f));
            graphics.setColor(Color.white);
            graphics.drawString(tooltip, bounds.x + bounds.width + 20,
                    bounds.y + (bounds.height / 2f) +
                            (graphics.getFontMetrics().getHeight() * 0.3f));
        }
    }

    void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    boolean isAnimationRunning() {
        return bounds.animationRunning;
    }

    protected abstract void mouseMoved(MouseEvent e);

    public void mouseMovedExternal(MouseEvent e) {
        hovering = false;
    }

    void mouseMovedInternal(MouseEvent e) {
        hovering = true;
        mouseMoved(e);
    }

    public abstract void onClick(MouseEvent e);

    public abstract void onMouseDown(MouseEvent e);

    public abstract void onMouseUp(MouseEvent e);

    void slerpBounds(Rect targetBounds, float speed) {
        bounds.animationRunning = true;
        bounds.animationTargetBounds = targetBounds;
        bounds.animationSpeed = speed;
    }

    public final void update() {
        bounds.update();
    }

    String[] wrapText(String text, int maxWidth, Graphics graphics, Font font) {
        String[] split = text.split(" ");
        ArrayList<String> sections = new ArrayList<String>();
        String temp = "";
        FontMetrics fm = graphics.getFontMetrics(font);
        for (String s : split) {
            if (fm.stringWidth(temp) - 1 + fm.stringWidth(s) > maxWidth) {
                sections.add(temp.substring(0, temp.length() - 1));
                temp = "";
            }
            temp += s + " ";
        }
        if (temp.length() > 0) {
            sections.add(temp.substring(0, temp.length() - 1));
        }
        return sections.toArray(new String[sections.size()]);
    }
}