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
	abstract void draw(Graphics2D graphics);

	/**
	 * Draws the {@link #tooltip} of the {@link com.dus.taxe.gui.GuiElement}, if one exists
	 * @param graphics
	 */
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
	 * @return the {@link com.dus.taxe.gui.GuiElement}'s {@link #tooltip}
	 */
	String getTooltip() {
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
	abstract void mouseMoved(MouseEvent e);

	/**
	 * Method that indicates that the mouse has moved outside of the {@link com.dus.taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	void mouseMovedExternal(MouseEvent e) {
		hovering = false;
	}


	/**
	 * Method that indicates that the mouse has moved inside the {@link com.dus.taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	void mouseMovedInternal(MouseEvent e) {
		hovering = true;
		mouseMoved(e);
	}

	/**
	 * Method that indicates that a button on the mouse has been clicked inside the {@link com.dus
	 * .taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	abstract void onClick(MouseEvent e);

	/**
	 * Method that indicates that a button on the mouse has been pushed down inside the {@link com
	 * .dus.taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	abstract void onMouseDown(MouseEvent e);

	/**
	 * Method that indicates that a button on the mouse has been released inside the {@link com.dus
	 * .taxe.gui.GuiElement}
	 *
	 * @param e the {@link java.awt.event.MouseEvent} object from the {@link javax.swing.JFrame}
	 */
	abstract void onMouseUp(MouseEvent e);

	/**
	 * Spherically interpolate the {@link #bounds} of the {@link com.dus.taxe.gui.GuiElement}
	 * from their current state to the target state
	 * @param targetBounds the target bounds that you are aiming for
	 * @param speed the speed at which to interpolate the {@link #bounds}
	 */
	void slerpBounds(Rect targetBounds, float speed) {
		bounds.animationRunning = true;
		bounds.animationTargetBounds = targetBounds;
		bounds.animationSpeed = speed;
	}

	/**
	 * Used to update the {@link #bounds} of the {@link com.dus.taxe.gui.GuiElement}, if they are being
	 * animated
	 */
	final void update() {
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
	 * wrapped text
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