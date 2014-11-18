package com.dus.taxe;

import java.util.ArrayList;

public class Route {
    ArrayList<Node> nodes = new ArrayList<Node>();

    public boolean isBlocked() {
        for (int i = 0; i < (nodes.size() - 1); i++) {
            if (Game.currentMap.connections[nodes.get(i).getId()][nodes.get(i + 1).getId()].getObstacle() != null) {
                return true;
            }
        }
        return false;
    }


}
