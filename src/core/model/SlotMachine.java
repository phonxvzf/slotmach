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
	private int addlerColumns = 0;
	private boolean allStop = false;
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
		/*
		 * for (int i = 0; i < (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 *
		 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2 - addlerColumns / 2) + 2; ++i)
		 * slotColumns.get(i).stop(); for (int i = (int) ((Settings.SLOT_DEFAULT_COLUMNS
		 * - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2 - addlerColumns / 2) + 2 +
		 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS; i < Settings.SLOT_DEFAULT_COLUMNS; ++i)
		 * slotColumns.get(i).stop();
		 */
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

	public void forceAllStop() {

		for (int i = 0; i < (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
				- addlerColumns / 2) + 2; ++i)
			slotColumns.get(i).stop();
		for (int i = (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
				- addlerColumns / 2) + 2 + Settings.SLOT_DEFAULT_BEGIN_COLUMNS; i < Settings.SLOT_DEFAULT_COLUMNS; ++i)
			slotColumns.get(i).stop();

	}

	public boolean pull() {
		if (pullCount < Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns) {
			// Stop slot column
			int beginStartCol = (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
					- addlerColumns / 2) + 2 + pullCount;
			slotColumns.get(beginStartCol).stop();
			// Get stopped slots information
			List<SlotType> columnInfo = slotColumns.get(beginStartCol).getSlotType();
			for (int i = 0; i < columnInfo.size(); ++i) {
				slotCell[i][beginStartCol] = columnInfo.get(i);
			}
			pullCount++;
			if (pullCount >= Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns)
				allStop = true;
			return true;
		}
		return false;
	}

	public boolean isAllStop() {
		return allStop;
	}

	public int getAddlerColumns() {
		return addlerColumns;
	}

	public void setAddlerColumns(int addlerColumn) {
		this.addlerColumns = addlerColumn;
	}

	public void reset() {
		allStop = false;
		pullCount = 0;
		int beginStartCol = (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
				- addlerColumns / 2) + 2;
		for (int i = beginStartCol; i < Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns + beginStartCol; ++i) {
			slotColumns.get(i).returnSpeed();
			slotColumns.get(i).setSlotFreeze(false);
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
