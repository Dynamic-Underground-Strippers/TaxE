package com.dus.taxe.gui;

import com.dus.taxe.Engine;
import com.dus.taxe.Engine.EngineType;
import com.dus.taxe.Game;
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
	private ButtonElement editRoute;
	private Train train;
	private static HashMap<EngineType, Image> images;

	public TrainGoalElement(Rect bounds) {
		super(bounds);
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
	}

	public Train getTrain() {
		return train;
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

	@Override
	public void draw(Graphics2D graphics) {
		editRoute.bounds = new Rect(bounds.x + 0.9f * bounds.width, bounds.y, bounds.height / 3f,
				bounds.height / 3f);
		if (train != null && images.get(train.getEngine().getType()) != null) {
			graphics.drawImage(images.get(train.getEngine().getType()), (int) bounds.x,
					(int) bounds.y, (int) bounds.width, (int) bounds.height, GUI.self);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	void setEditRouteButton() {
		editRoute = new ButtonElement(
				new Rect(bounds.x + 0.9f * bounds.width, bounds.y, bounds.height / 3f,
						bounds.height / 3f), "edit.png", new Runnable() {
			public void run() {
				GUI.settingRoute = true;

			}
		});
		editRoute.setTooltip("Set Route");
		GUI.self.addGuiElement(editRoute);
	}
}
