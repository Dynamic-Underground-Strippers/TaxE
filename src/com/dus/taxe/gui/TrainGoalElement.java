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
import javax.swing.JOptionPane;

class TrainGoalElement extends GuiElement {
	private static int count = 0;
	private static HashMap<EngineType, Image> images;
	private static Image redTrainIcon;
	private final int index;
	private ButtonElement editRouteButton;
	private Image icon;
	private Train train;

	TrainGoalElement(Rect bounds, int index) {
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
						ImageIO.read(getClass().getResourceAsStream("/rocket_side.png")));
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
		if (redTrainIcon == null) {
			try {
				redTrainIcon = ImageIO.read(getClass().getResourceAsStream("/train_red.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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
			if (count++ % 3 == 0) {
				for (Train t : Game.getOtherPlayer().getCurrentTrains()) {
					if (t.getRoute() != null) {
						Point p = t.getRoute().getCurrentNode().getLocation();
						graphics.drawImage(redTrainIcon, (int) (p.getX() * Screen.WIDTH - 15),
								(int) (p.getY() * Screen.HEIGHT - 15), 30, 30, GUI.self);
					}
				}
			}
			if (icon != null && train.getRoute() != null) {
				Point p = train.getRoute().getCurrentNode().getLocation();
				graphics.drawImage(icon, (int) (p.getX() * Screen.WIDTH - 15),
						(int) (p.getY() * Screen.HEIGHT - 15), 30, 30, GUI.self);
			}
			int count = 0;
			for (Upgrade u : train.getUpgrades()) {
				graphics.drawImage(ResourceContainer.upgradeImages.get(u.getType()),
						(int) (bounds.x + 130 * GUI.scale + (count++ * 120 * GUI.scale)),
						(int) (bounds.y + ((bounds.height - 100 * GUI.scale) / 2f)),
						(int) (100 * GUI.scale), (int) (100 * GUI.scale), GUI.self);
			}
		}
	}

	ButtonElement getEditRouteButton() {
		return editRouteButton;
	}

	int getIndex() {
		return index;
	}

	Train getTrain() {
		return train;
	}

	void setTrain(Train train) {
		this.train = train;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void onClick(MouseEvent e) {
		if (!isAnimationRunning()) {
			if (bounds.x == -490 * GUI.scale) {
				slerpBounds(new Rect(0, bounds.y, bounds.width, bounds.height), 0.075f);
			} else {
				slerpBounds(new Rect(-490 * GUI.scale, bounds.y, bounds.width, bounds.height),
						0.075f);
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
				if (!(train.getEngine().getName().equals(GUI.draggingResource.getName()))) {
					train.setEngine((Engine) GUI.draggingResource);
					Game.getCurrentPlayer().removeEngine((Engine) GUI.draggingResource);
					GUI.draggingRect = null;
					GUI.draggingImage = null;
					GUI.draggingResource = null;
				} else {
					JOptionPane.showMessageDialog(null,
							"This train already has that engine, cannot apply the engine again",
							"Train Already Has That Engine", JOptionPane.PLAIN_MESSAGE);
				}
			} else if (GUI.draggingResource instanceof Upgrade) {
				if (!train.hasUpgrade(GUI.draggingResource.getName())) {
					train.addUpgrade((Upgrade) GUI.draggingResource);
					Game.getCurrentPlayer().removeUpgrade((Upgrade) GUI.draggingResource);
					GUI.draggingRect = null;
					GUI.draggingImage = null;
					GUI.draggingResource = null;
				} else {
					JOptionPane.showMessageDialog(null,
							"This train already has that upgrade, cannot apply the upgrade again",
							"Train Already Has That Upgrade", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}

	void setEditRouteButton() {
		String image;
		switch (index) {
			case 0:
				image = "/edit_blue.png";
				break;
			case 1:
				image = "/edit_green.png";
				break;
			case 2:
				image = "/edit_pink.png";
				break;
			default:
				image = "/edit.png";
				break;
		}
		boolean addToGUI = editRouteButton == null;
		editRouteButton = new ButtonElement(
				new Rect(bounds.x + 0.9f * bounds.width, bounds.y, bounds.height / 3f,
						bounds.height / 3f), image, new Runnable() {
			public void run() {
				GUI.settingRoute = true;
				if (train.getRoute() != null) {
					GUI.tempRouteNodes.add(train.getRoute().getCurrentNode());
					GUI.tempRouteGoal = train.getGoal();
				}
				GUI.tempRouteTrainGoalElement = TrainGoalElement.this;
			}
		});
		editRouteButton.setTooltip("Set route");
		if (addToGUI) GUI.self.addGuiElement(editRouteButton);
	}
}
