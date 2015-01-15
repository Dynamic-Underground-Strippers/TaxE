package com.dus.taxe.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GuiElement {
    Rect bounds = new Rect();
    Rect srcBounds = new Rect();

    public GuiElement(Rect bounds) {
        this(bounds, bounds);
    }

    public GuiElement(Rect bounds, Rect srcBounds) {
        this.bounds = bounds;
        this.srcBounds = srcBounds;
    }

    public abstract void click(MouseEvent e);

    public abstract void draw(Graphics2D graphics);

    public abstract void mouseMoved(MouseEvent e);

    public void slerpBounds(Rect targetBounds, float speed) {
        bounds.animationRunning = true;
        bounds.animationTargetBounds = targetBounds;
        bounds.animationSpeed = speed;
    }

    public void slerpSrcBounds(Rect targetBounds, float speed) {
        srcBounds.animationRunning = true;
        srcBounds.animationTargetBounds = targetBounds;
        srcBounds.animationSpeed = speed;
    }

    public boolean isAnimationRunning() {
        return bounds.animationRunning || srcBounds.animationRunning;
    }

    public final void update() {
        bounds.update();
        srcBounds.update();
    }
}
