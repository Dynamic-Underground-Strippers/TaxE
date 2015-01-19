package com.dus.taxe.gui;

import com.dus.taxe.Game;
import com.dus.taxe.Goal;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GoalsContainer extends GuiElement {
    Color backgroundColour = new Color(0, 0, 0, 0.8f);
    float[] centres = new float[3];
    Rect[] nodeLinks = new Rect[6];
    Font normalFont;
    Font titleFont;

    GoalsContainer(Rect bounds) {
        super(bounds);
        titleFont = GUI.baseFont.deriveFont(30f);
        normalFont = GUI.baseFont.deriveFont(25f);
        for (int i = 0; i < 3; i++) {
            centres[i] = bounds.x + (bounds.width / 6f) + (i * (bounds.width / 3f));
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (nodeLinks[0] == null) {
            FontMetrics fm = graphics.getFontMetrics(normalFont);
            int height = fm.getHeight();
            int startWidth = fm.stringWidth("Start");
            int endWidth = fm.stringWidth("End");
            for (int i = 0; i < 3; i++) {
                nodeLinks[i * 2] = new Rect(bounds.x + bounds.width / 9f + (i * (bounds.width / 3f)) - startWidth / 2f, bounds.y + bounds.height * 0.8f - height * 0.8f, startWidth, height);
                nodeLinks[i * 2 + 1] = new Rect(bounds.x + (2 * bounds.width) / 9f + (i * (bounds.width / 3f)) - endWidth / 2f, bounds.y + bounds.height * 0.8f - height * 0.8f, endWidth, height);
            }
        }
        graphics.setColor(backgroundColour);
        graphics.fillRoundRect((int) bounds.x, (int) bounds.y, (int) bounds.width,
                (int) bounds.height, 10, 10);
        int count = 0;
        for (Goal g : Game.getCurrentPlayer().getCurrentGoals()) {
            graphics.setColor(Color.white);
            graphics.setFont(titleFont);
            int count2 = 1;
            for (String s : wrapText(g.getDescription(), (int) (bounds.width / 3.1f), graphics,
                    titleFont)) {
                graphics.drawString(s, centres[count] -
                                graphics.getFontMetrics(titleFont).stringWidth(s) / 2f,
                        bounds.y + bounds.height * 0.1f +
                                count2++ * graphics.getFontMetrics(titleFont).getHeight());
            }
            graphics.setFont(normalFont);
            graphics.setColor(Color.CYAN);
            graphics.drawString("Start", nodeLinks[count * 2].x, nodeLinks[count * 2].y + nodeLinks[count * 2].height * 0.8f);
            graphics.setColor(Color.GREEN);
            graphics.drawString("End", nodeLinks[count * 2 + 1].x, nodeLinks[count * 2 + 1].y + nodeLinks[count * 2 + 1].height * 0.8f);
            count++;
        }
    }

    @Override
    protected void mouseMoved(MouseEvent e) {
        for (int i = 0; i < nodeLinks.length; i++) {
            if (nodeLinks[i].contains(e.getPoint())) {
                GUI.self.setCursor(Cursor.HAND_CURSOR);
                if (i % 2 == 0) {
                    Game.getCurrentPlayer().getCurrentGoals().get(i / 2);
                } else {

                }
                return;
            }
        }
        GUI.self.setCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onMouseDown(MouseEvent e) {

    }

    @Override
    public void onMouseUp(MouseEvent e) {

    }
}
