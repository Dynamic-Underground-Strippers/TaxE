package com.dus.taxe.gui;

import java.awt.*;

class Rect {
    public float height;
    public float width;
    public float x;
    public float y;
    boolean animationRunning;
    float animationSpeed;
    Rect animationTargetBounds;

    public Rect() {
        this(0, 0, 0, 0);
    }

    public Rect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean contains(float x, float y) {
        return x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height;
    }

    public boolean contains(Point p) {
        return contains(p.x, p.y);
    }

    public boolean contains(com.dus.taxe.Point p) {
        return contains(p.getX(), p.getY());
    }

    public boolean equals(Object other) {
        if (other instanceof Rect) {
            Rect r = (Rect) other;
            return x == r.x && y == r.y && width == r.width && height == r.height;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Rect(x:" + x + ", y:" + y + ", width:" + width + ", height:" + height + ")";
    }

    public final void update() {
        if (animationRunning) {
            x += (animationTargetBounds.x - x) * animationSpeed * (GUI.frameTime / 32f);
            y += (animationTargetBounds.y - y) * animationSpeed * (GUI.frameTime / 32f);
            width += (animationTargetBounds.width - width) * animationSpeed * (GUI.frameTime / 32f);
            height += (animationTargetBounds.height - height) * animationSpeed *
                    (GUI.frameTime / 32f);
            GUI.self.repaint();
            if (Math.abs(x - animationTargetBounds.x) < 1 &&
                    Math.abs(y - animationTargetBounds.y) < 1 &&
                    Math.abs(width - animationTargetBounds.width) < 1 &&
                    Math.abs(height - animationTargetBounds.height) < 1) {
                animationRunning = false;
                x = animationTargetBounds.x;
                y = animationTargetBounds.y;
                width = animationTargetBounds.width;
                height = animationTargetBounds.height;
            }
        }
    }
}
