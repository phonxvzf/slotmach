package core.model;

import java.util.ArrayList;
import java.util.List;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.gfx.Sprite;
import core.settings.Settings;
import javafx.scene.canvas.GraphicsContext;

public class SlotMachine extends BasicEntity {

	private List<SlotColumn> slotColumns = new ArrayList<SlotColumn>();
	private int pullCount = 0;
	private double minSlotVelocityY = Settings.SLOT_DEFAULT_VELOCITY;
	private SlotType[][] slotCell;
	
	public SlotMachine(GraphicsContext gc, Sprite background, double x, double y, int columns) {
		super(
				background,
				x - (background.getWidth() - columns * AssetCache.getImage(AssetID.K_IMG).getWidth()) / 2,
				y + (Settings.SLOT_DEFAULT_COLUMN_HEIGHT - background.getHeight()) / 2
			);
		for (int i = 0; i < columns; ++i) {
			slotColumns.add(
					new SlotColumn(
							gc, 
							x + i * Settings.SLOT_DEFAULT_WIDTH,
							y,
							Settings.SLOT_DEFAULT_COLUMN_HEIGHT
							)
					);
		}
		slotCell = new SlotType[(int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / AssetCache.getImage(AssetID.K_IMG).getHeight())][columns];
	}
	
	public void update(long dt) {
		for (SlotColumn slotColumn : slotColumns) {
			if (slotColumn.getSlotVelocityY() < minSlotVelocityY) 
				slotColumn.setSlotVelocityY(minSlotVelocityY);
			slotColumn.moveSlots(dt);
		}
	}
	
	@Override
	public void draw() {
		sprite.draw(posX, posY);
		for (SlotColumn slotColumn : slotColumns) {
			slotColumn.draw();
		}
	}
	
	public boolean pullLever() {
		if (pullCount < slotColumns.size()) {
			slotColumns.get(pullCount).setSlotFreeze(true);
			slotColumns.get(pullCount).stop();
			pullCount++;
			return true;
		}
		return false;
	}
	
	public void resetPullCount() {
		pullCount = 0;
		for (SlotColumn slotColumn : slotColumns) {
			slotColumn.setSlotFreeze(false);
		}
	}

	public void slowDown(double decel, double minVel) {
		minSlotVelocityY = minVel;
		for (SlotColumn col : slotColumns) {
			col.setSlotAccelY(decel);
		}
	}
	
	public void returnSpeed() {
		for (SlotColumn col : slotColumns) {
			col.setSlotAccelY(0);
			col.setSlotVelocityY(Settings.SLOT_DEFAULT_VELOCITY);
		}
	}
	
}
