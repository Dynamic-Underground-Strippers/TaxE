package com.dus.taxe.gui;

import java.awt.*;

public class Rect {
    public float x;
    public float y;
    public float width;
    public float height;

    public Rect() {
        this(0, 0, 0, 0);
    }

    public Rect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(float x, float y) {
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
}
