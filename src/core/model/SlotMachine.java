package core.model;

import java.util.ArrayList;
import java.util.List;

import core.asset.gfx.Sprite;
import core.settings.Settings;
import javafx.scene.canvas.GraphicsContext;

public class SlotMachine extends BasicEntity {

	private List<SlotColumn> slotColumns = new ArrayList<SlotColumn>();
	private int pullCount = 0;
	
	public SlotMachine(GraphicsContext gc, Sprite background, double x, double y, int columns) {
		super(background,x-(Settings.WINDOW_WIDTH / 2 -75) +(Settings.WINDOW_WIDTH-background.getWidth())/2, (Settings.WINDOW_HEIGHT-background.getHeight())/2);
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
	}
	
	public void update(long dt) {
		for (SlotColumn slotColumn : slotColumns) {
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

}
