package com.dus.taxe.gui;

import com.dus.taxe.Engine.EngineType;
import com.dus.taxe.Game;
import com.dus.taxe.Upgrade.UpgradeType;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ResourceContainer extends GuiElement {
    private static int PADDING;
    private final Color backgroundColour = new Color(0, 0, 0, 0.8f);
    private final HashMap<EngineType, Image> engineImages = new HashMap<EngineType, Image>();
    private final Rect[] engineRects = new Rect[3];
    private final HashMap<UpgradeType, Image> upgradeImages = new HashMap<UpgradeType, Image>();
    private final Rect[] upgradeRects = new Rect[4];

    public ResourceContainer(Rect bounds) {
        super(bounds);
        PADDING = (int) (10 * GUI.scale);
        //noinspection ConstantConditions
        engineImages.put(EngineType.HAND_CART,
                new ImageIcon(getClass().getClassLoader().getResource("engine_handcart.png"))
                        .getImage());
        //noinspection ConstantConditions
        engineImages.put(EngineType.STEAM,
                new ImageIcon(getClass().getClassLoader().getResource("engine_steam.png"))
                        .getImage());
        //noinspection ConstantConditions
        engineImages.put(EngineType.DIESEL,
                new ImageIcon(getClass().getClassLoader().getResource("engine_diesel.png"))
                        .getImage());
        //noinspection ConstantConditions
        engineImages.put(EngineType.ELECTRIC,
                new ImageIcon(getClass().getClassLoader().getResource("engine_electric.png"))
                        .getImage());
        //noinspection ConstantConditions
        engineImages.put(EngineType.ROCKET,
                new ImageIcon(getClass().getClassLoader().getResource("engine_rocket.png"))
                        .getImage());
        //noinspection ConstantConditions
        upgradeImages.put(UpgradeType.DOUBLE_SPEED,
                new ImageIcon(getClass().getClassLoader().getResource("upgrade_doublespeed.png"))
                        .getImage());
        //noinspection ConstantConditions
        upgradeImages.put(UpgradeType.TELEPORT,
                new ImageIcon(getClass().getClassLoader().getResource("upgrade_teleport.png"))
                        .getImage());
        int imageSize = (int) ((bounds.height - PADDING * 8f) / 7f);
        for (int i = 0; i < engineRects.length; i++) {
            engineRects[i] = new Rect(PADDING, i * (imageSize + PADDING) + PADDING, imageSize,
                    imageSize);
        }
        for (int i = 0; i < upgradeRects.length; i++) {
            upgradeRects[i] = new Rect(PADDING,
                    i * (imageSize + PADDING) + engineRects.length * (imageSize + PADDING) +
                            PADDING, imageSize, imageSize);
        }
    }


    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(backgroundColour);
        graphics.fillRoundRect((int) bounds.x, (int) bounds.y, (int) bounds.width,
                (int) bounds.height, PADDING, PADDING);
        for (int i = 0; i < Game.getCurrentPlayer().getEngineInventory().size(); i++) {
            if (Game.getCurrentPlayer().getEngineInventory().get(i) != null) {
                graphics.drawImage(engineImages
                                .get(Game.getCurrentPlayer().getEngineInventory().get(i).getType()),
                        (int) (bounds.x + engineRects[i].x), (int) (bounds.y + engineRects[i].y),
                        (int) (engineRects[i].width), (int) (engineRects[i].height), GUI.self);
            }
        }
        for (int i = 0; i < Game.getCurrentPlayer().getUpgradeInventory().size(); i++) {
            graphics.drawImage(upgradeImages
                            .get(Game.getCurrentPlayer().getUpgradeInventory().get(i).getType()),
                    (int) (bounds.x + upgradeRects[i].x), (int) (bounds.y + upgradeRects[i].y),
                    (int) (upgradeRects[i].width), (int) (upgradeRects[i].height), GUI.self);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        p.x -= bounds.x;
        p.y -= bounds.y;
        for (int i = 0; i < engineRects.length; i++) {
            if (engineRects[i].contains(p) && i < Game.getCurrentPlayer().getEngineInventory().size()) {
                GUI.self.setCursor(Cursor.HAND_CURSOR);
                return;
            }
        }
        for (int i = 0; i < upgradeRects.length; i++) {
            if (upgradeRects[i].contains(p) && i < Game.getCurrentPlayer().getUpgradeInventory().size()) {
                GUI.self.setCursor(Cursor.HAND_CURSOR);
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
        Point p = e.getPoint();
        p.x -= bounds.x;
        p.y -= bounds.y;
        for (int i = 0; i < Game.getCurrentPlayer().getEngineInventory().size(); i++) {
            if (engineRects[i].contains(p)) {
                GUI.draggingRect = new Rect(engineRects[i].x + bounds.x,
                        engineRects[i].y + bounds.y, engineRects[i].width, engineRects[i].height);
                GUI.draggingImage = engineImages
                        .get(Game.getCurrentPlayer().getEngineInventory().get(i).getType());
                GUI.draggingResource = Game.getCurrentPlayer().getEngineInventory().get(i);
            }
        }
        for (int i = 0; i < Game.getCurrentPlayer().getUpgradeInventory().size(); i++) {
            if (upgradeRects[i].contains(p)) {
                GUI.draggingRect = new Rect(upgradeRects[i].x + bounds.x,
                        upgradeRects[i].y + bounds.y, upgradeRects[i].width,
                        upgradeRects[i].height);
                GUI.draggingImage = upgradeImages
                        .get(Game.getCurrentPlayer().getUpgradeInventory().get(i).getType());
                GUI.draggingResource = Game.getCurrentPlayer().getUpgradeInventory().get(i);
            }
        }
    }

    @Override
    public void onMouseUp(MouseEvent e) {

    }

//	public void addResource(Resource resource) {
//		if (resource instanceof Engine) {
//			for (int i = 0; i < engines.length; i++) {
//				if (engines[i] == null) {
//					engines[i] = (Engine) resource;
//					break;
//				}
//			}
//		} else if (resource instanceof Upgrade) {
//			for (int i = 0; i < upgrades.length; i++) {
//				if (upgrades[i] == null) {
//					upgrades[i] = (Upgrade) resource;
//					break;
//				}
//			}
//		}
//	}

//	public void removeAllResources() {
//		engines = new Engine[3];
//		upgrades = new Upgrade[4];
//	}
}