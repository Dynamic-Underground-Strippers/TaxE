package com.dus.taxe.gui;

import com.dus.taxe.Goal;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrainGoalElement extends GuiElement {
	private Goal goal;
	private BufferedImage image;
	private ButtonElement editRoute;

	public TrainGoalElement(Rect bounds) {
		super(bounds);
		editRoute = new ButtonElement(new Rect(bounds.x + 0.9f * bounds.width, bounds.y,
				(bounds.height / 3f) * (600f / 218f), bounds.height / 3f), "edit.png", new Runnable
				() {
			public void run() {
				GUI.settingRoute = true;

			}
		});
		GUI.self.addGuiElement(editRoute);
	}

	@Override
	public void click(MouseEvent e) {
		if (!isAnimationRunning()) {
			if (bounds.x == -490) {
				slerpBounds(new Rect(0, bounds.y, bounds.width, bounds.height), 0.075f);
			} else {
				slerpBounds(new Rect(-490, bounds.y, bounds.width, bounds.height), 0.075f);
			}
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		if (image != null) {
			graphics.drawImage(image, (int) bounds.x, (int) bounds.y, (int) bounds.width,
					(int) bounds.height, GUI.self);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
		if (goal == null || goal.getCurrentTrain() == null) {
			image = null;
		} else {
			switch (goal.getCurrentTrain().getEngine().getType()) {
				case HAND_CART:
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/handcart_side.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case STEAM:
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/steam_side.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case DIESEL:
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/diesel_side.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case ELECTRIC:
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case ROCKET:
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/electric_side.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
			}
		}
	}
}
