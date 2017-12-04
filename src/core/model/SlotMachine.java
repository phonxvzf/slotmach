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
	private int columns;
	private SlotType[][] slotCell;

	public SlotMachine(Sprite background, double x, double y, int columns) {
		super(background, x - (background.getWidth() - columns * AssetCache.getImage(AssetID.K_IMG).getWidth()) / 2,
				y + (Settings.SLOT_DEFAULT_COLUMN_HEIGHT - background.getHeight()) / 2);
		this.columns = columns;
		for (int i = 0; i < columns; ++i) {
			slotColumns
					.add(new SlotColumn(x + i * Settings.SLOT_DEFAULT_WIDTH, y, Settings.SLOT_DEFAULT_COLUMN_HEIGHT));
		}
		slotCell = new SlotType[(int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT
				/ AssetCache.getImage(AssetID.K_IMG).getHeight())][columns];
	}

	public void update(long dt) {
		for (SlotColumn col : slotColumns) {
			col.update(dt);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		sprite.draw(gc, posX, posY);
		for (SlotColumn slotColumn : slotColumns) {
			slotColumn.draw(gc);
		}
	}

	public boolean pull() {
		if (pullCount < slotColumns.size()) {
			// Stop slot column
			slotColumns.get(pullCount).stop();
			
			// Get stopped slots information
			List<SlotType> columnInfo = slotColumns.get(pullCount).getSlotType();
			for (int i = 0; i < columnInfo.size(); ++i) { 
				slotCell[i][pullCount] = columnInfo.get(i);
			}
			System.out.println("");
			pullCount++;
			return true;
		}
		return false;
	}

	public void reset() {
		pullCount = 0;
		for (SlotColumn col : slotColumns) {
			col.returnSpeed();
			col.setSlotFreeze(false);
		}
	}

	public void slowDown() {
		for (SlotColumn col : slotColumns) {
			col.slowDown();
		}
	}

	public void returnSpeed() {
		for (SlotColumn col : slotColumns) {
			col.returnSpeed();
		}
	}

	public SlotColumn getSlotColumn(int i) {
		return slotColumns.get(i);
	}

	public int getPullCount() {
		return pullCount;
	}

	public int getColumns() {
		return columns;
	}

	public SlotType[][] getSlotCells() {
		return slotCell;
	}

	public boolean isSlowDown() {
		boolean allSlow = true;
		for (SlotColumn x : slotColumns) {
			allSlow = allSlow && x.isPulled();
		}
		return allSlow;
	}
}
