package com.dus.taxe.gui;

import com.dus.taxe.Game;
import com.dus.taxe.Goal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GoalsContainer extends GuiElement {
	Color backgroundColour = new Color(0, 0, 0, 0.8f);
	Font titleFont;
	Font normalFont;
	float[] centres = new float[3];

	GoalsContainer(Rect bounds) {
		super(bounds);
		titleFont = GUI.baseFont.deriveFont(30f);
		normalFont = GUI.baseFont.deriveFont(15f);
		for (int i = 0; i < 3; i++) {
			centres[i] = bounds.x + (bounds.width / 6f) + (i * (bounds.width / 3f));
		}
	}

	boolean b = false;

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(backgroundColour);
		graphics.fillRoundRect((int) bounds.x, (int) bounds.y, (int) bounds.width,
				(int) bounds.height, 10, 10);
		int count = 0;
		graphics.setFont(titleFont);
		for (Goal g : Game.getCurrentPlayer().getCurrentGoals()) {
			int count2 = 0;
			for (String s : wrapText(g.getDescription(), (int) (bounds.width / 3f), graphics,
					titleFont)) {
				graphics.drawString(s, centres[count] -
						graphics.getFontMetrics(titleFont).stringWidth(g.getDescription()) / 2f,
						bounds.y + bounds.height * 0.2f +
								count2 * graphics.getFontMetrics(titleFont).getHeight());
			}
			count++;
			break;
		}
		if (!b) {
			b = true;
			System.out.println(Game.getCurrentPlayer().getCurrentGoals().get(0).getDescription());
			for (String s : wrapText(
					Game.getCurrentPlayer().getCurrentGoals().get(0).getDescription(), 300,
					graphics, titleFont)) {
				System.out.println(s);
			}
		}
	}

	@Override
	protected void mouseMoved(MouseEvent e) {

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
