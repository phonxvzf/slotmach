package core.model;

import java.util.ArrayList;
import java.util.List;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.gfx.Sprite;
import core.game.GameModel;
import core.game.GameState;
import core.settings.Settings;
import javafx.scene.canvas.GraphicsContext;

public class SlotMachine extends BasicEntity {

	private List<SlotColumn> slotColumns = new ArrayList<SlotColumn>();
	private int pullCount = 0;
	private int columns;
	private int addlerColumns = 0;
	private int addlerRow = 0;
	private boolean allStop = false;
	private SlotType[][] slotCell;
	private boolean buyCol = false;
	private SlotType buyColx = null;

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
		stopAll();
	}

	public void update(long dt) {
		if (buyCol)
			buyColumn();
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
			if (pullCount >= Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns) {
				allStop = true;
			}
			return true;
		}
		allStop = false;
		return false;
	}

	private void buyColumn() {
		int beginStartCol = (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
				- addlerColumns / 2) + 2 + pullCount;
		slotColumns.get(beginStartCol).setBuyCol(true);
		slotColumns.get(beginStartCol).setX(buyColx);
		this.buyCol = false;
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
		pullCount = 0;
		allStop = false;
		int beginStartCol = (int) ((Settings.SLOT_DEFAULT_COLUMNS - 2 * Settings.SLOT_DEFAULT_BEGIN_COLUMNS) / 2
				- addlerColumns / 2) + 2;
		for (int i = beginStartCol; i < Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns + beginStartCol; ++i) {
			slotColumns.get(i).setBuyCol(false);
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

	public void stopAll() {
		int startCol = (Settings.SLOT_DEFAULT_COLUMNS / 2 - 1) - addlerColumns / 2;
		for (int j = 0; j < Settings.SLOT_DEFAULT_BEGIN_COLUMNS + addlerColumns; ++j) {
			slotColumns.get(startCol + j).implicitStop();
			pullCount++;
		}
		allStop = true;
	}

	public boolean isSlowDown() {
		boolean allSlow = true;
		for (SlotColumn x : slotColumns) {
			allSlow = allSlow && x.isPulled();
		}
		return allSlow;
	}

	public int getAddlerRow() {
		return addlerRow;
	}

	public void setAddlerRow(int addlerRow) {
		this.addlerRow = addlerRow;
	}

	public boolean isBuyCol() {
		return buyCol;
	}

	public void setBuyCol(boolean buyCol) {
		this.buyCol = buyCol;
	}

	public SlotType getBuyColx() {
		return buyColx;
	}

	public void setBuyColx(SlotType buyColx) {
		this.buyColx = buyColx;
	}

}
