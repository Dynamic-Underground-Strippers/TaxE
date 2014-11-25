package com.dus.taxe.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class GuiElement extends Rect {
	private ArrayList<GuiElement> children = new ArrayList<GuiElement>();
	private boolean mouseHover = false;
	private GuiElement parent;
	private int zIndex;

	public GuiElement(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(Graphics graphics) {
		HashMap<Integer, ArrayList<GuiElement>> map = new HashMap<Integer, ArrayList<GuiElement>>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (GuiElement child : children) {
			if (!indices.contains(child.getZIndex())) {
				indices.add(child.getZIndex());
				map.put(child.getZIndex(), new ArrayList<GuiElement>());
			}
			map.get(child.getZIndex()).add(child);
		}
		Collections.sort(indices);
		for (Integer i : indices) {
			for (GuiElement guiElement : map.get(i)) {
				guiElement.draw(graphics);
			}
		}
	}

	public GuiElement getParent() {
		return parent;
	}

	public int getZIndex() {
		return zIndex;
	}

	protected void mouseMoved(MouseEvent e) {
		if (contains(e.getPoint())) {
			if (!mouseHover) {
				onMouseOver();
			}
			mouseHover = true;
		} else {
			if (mouseHover) {
				onMouseOut();
			}
			mouseHover = false;
		}
		for (GuiElement child : children) {
			child.mouseMoved(e);
		}
	}

	public final void addChild(GuiElement child) {
		child.setParent(this);
		children.add(child);
	}

	protected void onMouseOver() {
	}

	protected void onMouseOut() {
	}

	public void setParent(GuiElement parent) {
		this.parent = parent;
	}

	public GuiElement getChild(int index) {
		return children.get(index);
	}

	public boolean contains(Point point) {
		return contains(point.x, point.y);
	}

	public boolean contains(float x, float y) {
		float w = width;
		float h = height;
		if (w < 0 || h < 0) {
			// At least one of the dimensions is negative...
			return false;
		}
		// Note: if either dimension is zero, tests below must return false...
		float x2 = this.x + (parent == null ? 0 : parent.x);
		float y2 = this.y + (parent == null ? 0 : parent.y);
		if (x < x2 || y < y2) {
			return false;
		}
		w += x2;
		h += y2;
		//    overflow || intersect
		return ((w < x2 || w > x) && (h < y2 || h > y));
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
}
