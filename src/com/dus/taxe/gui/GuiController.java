package com.dus.taxe.gui;

import com.dus.taxe.Map;

public class GuiController {
    private static GUI gui;

    public static void init(GUI gui) {
        GuiController.gui = gui;
    }

    public static void setMap(Map map) {
        gui.map = map;
        gui.repaint();
    }

    public static void displayMessage(String message) {
    }
}
