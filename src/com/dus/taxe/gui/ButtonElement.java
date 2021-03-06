package com.dus.taxe.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * General class for button-like {@link com.dus.taxe.gui.GuiElement}s
 */
class ButtonElement extends GuiElement {
	private final Runnable action;
	private final Color colour = new Color(0, 0, 0, 0.8f);
	private Font font;
	private Image image;
	private String text;

	public ButtonElement(Rect bounds, String imageName, Runnable action) {
		super(bounds);
		this.action = action;
		if (imageName != null) {
			try {
				image = ImageIO.read(getClass().getResourceAsStream(imageName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ButtonElement(Rect bounds, String text, Font font, Runnable action) {
		super(bounds);
		this.text = text;
		this.font = font;
		this.action = action;
	}

	@Override
	public void draw(Graphics2D graphics) {
		if (image != null) {
			graphics.drawImage(image, (int) bounds.x, (int) bounds.y, (int) bounds.width,
					(int) bounds.height, GUI.self);
		} else {
			graphics.setColor(colour);
			bounds.width = graphics.getFontMetrics(font).stringWidth(text) + 20 * GUI.scale;
			bounds.height = graphics.getFontMetrics(font).getHeight() * 1.2f;
			graphics.fillRoundRect((int) bounds.x, (int) bounds.y, (int) bounds.width,
					(int) bounds.height, 10, 10);
			graphics.setFont(font);
			graphics.setColor(Color.white);
			graphics.drawString(text, bounds.x + graphics.getFontMetrics(font).getHeight() * 0.1f,
					bounds.y + bounds.height * 0.8f);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.HAND_CURSOR);
	}

	@Override
	public void onClick(MouseEvent e) {
		if (action != null) action.run();
	}

	@Override
	public void onMouseDown(MouseEvent e) {

	}

	@Override
	public void onMouseUp(MouseEvent e) {

	}
}
