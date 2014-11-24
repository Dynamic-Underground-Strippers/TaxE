package com.dus.taxe;

import java.awt.*;

public abstract class Node {
    private final int id;
    private final Point location;
    private final String name;

    public Node(int id, String name, Point location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Point getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        String retStr = Integer.toString(id) + "," + location.toString() + "," + name;
        return retStr;
    }


}

