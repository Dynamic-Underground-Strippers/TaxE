package com.dus.taxe.gui;

import com.dus.taxe.Engine;
import com.dus.taxe.Engine.EngineType;
import com.dus.taxe.Game;
import com.dus.taxe.Point;
import com.dus.taxe.Train;
import com.dus.taxe.Upgrade;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TrainGoalElement extends GuiElement {
	private static HashMap<EngineType, Image> images;
	private final int index;
	private ButtonElement editRouteButton;
	private Train train;
	private Image icon;

	public TrainGoalElement(Rect bounds, int index) {
		super(bounds);
		this.index = index;
		if (images == null) {
			images = new HashMap<EngineType, Image>();
			try {
				images.put(EngineType.HAND_CART,
						ImageIO.read(getClass().getResourceAsStream("/handcart_side.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				images.put(EngineType.STEAM,
						ImageIO.read(getClass().getResourceAsStream("/steam_side.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				images.put(EngineType.DIESEL,
						ImageIO.read(getClass().getResourceAsStream("/diesel_side.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				images.put(EngineType.ELECTRIC,
						ImageIO.read(getClass().getResourceAsStream("/electric_side.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				images.put(EngineType.ROCKET,
						ImageIO.read(getClass().getResourceAsStream("/electric_side.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			switch (index) {
				case 0:
					icon = ImageIO.read(getClass().getResourceAsStream("/train_blue.png"));
					break;
				case 1:
					icon = ImageIO.read(getClass().getResourceAsStream("/train_green.png"));
					break;
				case 2:
					icon = ImageIO.read(getClass().getResourceAsStream("/train_pink.png"));
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean b = false;

	@Override
	public void draw(Graphics2D graphics) {
		editRouteButton.bounds = new Rect(bounds.x + 0.9f * bounds.width, bounds.y,
				bounds.height / 3f, bounds.height / 3f);
		editRouteButton.setTooltip(train.getRoute() == null ? "Set route" : "Edit route");
		if (train != null) {
			if (images.get(train.getEngine().getType()) != null) {
				graphics.drawImage(images.get(train.getEngine().getType()), (int) bounds.x,
						(int) bounds.y, (int) bounds.width, (int) bounds.height, GUI.self);
			}
			if (icon != null && train.getRoute() != null) {
				Point p = train.getRoute().getCurrentNode().getLocation();
				if (!b) {
					b = true;
					System.out.println(p);
				}
				graphics.drawImage(icon, (int) (p.getX() * Screen.WIDTH - 15),
						(int) (p.getY() * Screen.HEIGHT - 15), 30, 30, GUI.self);
			}

		}
	}

	public ButtonElement getEditRouteButton() {
		return editRouteButton;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void onClick(MouseEvent e) {
		if (!isAnimationRunning()) {
			if (bounds.x == -490) {
				slerpBounds(new Rect(0, bounds.y, bounds.width, bounds.height), 0.075f);
			} else {
				slerpBounds(new Rect(-490, bounds.y, bounds.width, bounds.height), 0.075f);
			}
		}
	}

	@Override
	public void onMouseDown(MouseEvent e) {

	}

	@Override
	public void onMouseUp(MouseEvent e) {
		if (GUI.draggingRect != null && GUI.draggingImage != null && GUI.draggingResource != null) {
			if (GUI.draggingResource instanceof Engine) {
				train.setEngine((Engine) GUI.draggingResource);
				Game.getCurrentPlayer().removeEngine((Engine) GUI.draggingResource);
				GUI.draggingRect = null;
				GUI.draggingImage = null;
				GUI.draggingResource = null;
			}
			if (GUI.draggingResource instanceof Upgrade) {
				train.addUpgrade((Upgrade) GUI.draggingResource);
				Game.getCurrentPlayer().removeUpgrade((Upgrade) GUI.draggingResource);
				GUI.draggingRect = null;
				GUI.draggingImage = null;
				GUI.draggingResource = null;
			}
		}
		GUI.self.repaint();
	}

	void setEditRouteButton() {
		String image;
		switch (index) {
			case 0:
				image = "edit_blue.png";
				break;
			case 1:
				image = "edit_green.png";
				break;
			case 2:
				image = "edit_pink.png";
				break;
			default:
				image = "edit.png";
				break;
		}
		boolean addToGUI = editRouteButton == null;
		editRouteButton = new ButtonElement(
				new Rect(bounds.x + 0.9f * bounds.width, bounds.y, bounds.height / 3f,
						bounds.height / 3f), image, new Runnable() {
			public void run() {
				GUI.settingRoute = true;
				GUI.tempRouteTrainGoalElement = TrainGoalElement.this;
			}
		});
		editRouteButton.setTooltip("Set route");
		if (addToGUI) GUI.self.addGuiElement(editRouteButton);
	}
}
