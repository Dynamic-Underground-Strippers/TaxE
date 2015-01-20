package com.dus.taxe.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A base class for all GUI objects
 */
abstract class GuiElement {
	Rect bounds = new Rect();
	private boolean hovering = false;
	private String tooltip;

	GuiElement(Rect bounds) {
		this.bounds = bounds;
	}

	/**
	 * Method that will contain drawing code
	 *
	 * @param graphics The {@link java.awt.Graphics2D} object from the {@link javax.swing.JFrame}
	 */
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
			draw(null);
		}
	}

	/**
	 * @return the {@link com.dus.taxe.gui.GuiElement}'s tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * @param tooltip the text to set the {@link com.dus.taxe.gui.GuiElement}'s tooltip to
	 */
	void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @return whether or not this {@link com.dus.taxe.gui.GuiElement}'s bounds are currently
	 * being animated or not
	 */
	boolean isAnimationRunning() {
		return bounds.animationRunning;
	}

	/**
	 * Method that will handle mouseMoved events
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	protected abstract void mouseMoved(MouseEvent e);

	/**
	 * Method that indicates that the mouse has moved outside of the {@link com.dus.taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
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

	/**
	 * Utility method to facilitate wrapping text
	 *
	 * @param text     the text to wrap
	 * @param maxWidth the maximum width that each line of text can be
	 * @param graphics the {@link java.awt.Graphics} object from the {@link javax.swing.JFrame}
	 * @param font     the font to use to determine text width/height from
	 * @return a {@link java.lang.String} array, with each element representing a single line of
	 * wrapped tetx
	 */
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