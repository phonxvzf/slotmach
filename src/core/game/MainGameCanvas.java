package core.game;

import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.settings.Settings;
import javafx.scene.paint.Color;

public class MainGameCanvas extends GameCanvas {

	private StaticSprite frozenSprite = new StaticSprite(AssetID.FROZEN_IMG);
	private StaticSprite boxExtends = new StaticSprite(AssetID.BOX_IMG);
	private StaticSprite vshadowTop = new StaticSprite(AssetID.VSHADOW_TOP_IMG);
	private StaticSprite vshadowBottom = new StaticSprite(AssetID.VSHADOW_BOTTOM_IMG);
	private StaticSprite hshadowLeft = new StaticSprite(AssetID.HSHADOW_LEFT_IMG);
	private StaticSprite hshadowRight = new StaticSprite(AssetID.HSHADOW_RIGHT_IMG);

	public MainGameCanvas(GameModel model, double width, double height) {
		super(model, width, height);
	}

	private void drawBlocks() {
		int beginColLeft = (int) ((Settings.GAME_CANVAS_WIDTH - Settings.SLOT_DEFAULT_COLUMNS * 50) / 2);
		int endColLeft = 50 * (Settings.SLOT_DEFAULT_COLUMNS / 2 - Settings.SLOT_DEFAULT_BEGIN_COLUMNS / 2
				- gameModel.slotMachine.getAddlerColumns() / 2);
		int beginColRight = beginColLeft + endColLeft
				+ 50 * (Settings.SLOT_DEFAULT_BEGIN_COLUMNS + gameModel.slotMachine.getAddlerColumns());
		for (int j = 0; j < Settings.GAME_CANVAS_HEIGHT; j += 50) {
			for (int i = beginColLeft; i < endColLeft + beginColLeft; i += 50) {
				boxExtends.draw(gc, i, j);
			}
			for (int i = beginColRight; i < endColLeft + beginColRight; i += 50) {
				boxExtends.draw(gc, i, j);
			}
		}
		int endRowUp = 50
				* (Settings.SLOT_DEFAULT_ROWS - Settings.SLOT_DEFAULT_BEGIN_ROWS - gameModel.slotMachine.getAddlerRow())
				/ 2;
		for (int i = 0; i < endRowUp; i += 50) {
			for (int j = endColLeft + beginColLeft; j < beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}
		int beginRowDown = endRowUp + 50 * (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow());
		for (int i = beginRowDown; i < Settings.GAME_CANVAS_HEIGHT; i += 50) {
			for (int j = endColLeft + beginColLeft; j < beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}
	}

	private void drawIce() {
		gc.setGlobalAlpha(gameModel.slotMachine.isAllStop() ? 0.0f
				: (Settings.SLOT_DEFAULT_VELOCITY
						- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
						/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY));

		gc.setGlobalAlpha(gameModel.slotMachine.isAllStop() ? 0.0f
				: (Settings.SLOT_DEFAULT_VELOCITY
						- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
						/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY));
		frozenSprite.draw(gc, 0, 0);
	}

	private void drawShadow() {
		int startRow = ((int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH)
				- (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow())) / 2;
		int startCol = (Settings.SLOT_DEFAULT_COLUMNS / 2 - 1) - gameModel.slotMachine.getAddlerColumns() / 2;
		int rowCount = Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow();
		int colCount = Settings.SLOT_DEFAULT_BEGIN_COLUMNS + gameModel.slotMachine.getAddlerColumns();

		double pX = (Settings.GAME_CANVAS_WIDTH - (Settings.SLOT_DEFAULT_COLUMNS * Settings.SLOT_DEFAULT_WIDTH)) / 2
				+ startCol * Settings.SLOT_DEFAULT_WIDTH;
		double pY = startRow * Settings.SLOT_DEFAULT_WIDTH;
		
		// Draw vertical shadow
		for (int i = 0; i < colCount; ++i) {
			vshadowTop.draw(gc, pX + i * Settings.SLOT_DEFAULT_WIDTH, pY);
		}
		double newpY = pY + (rowCount - 1) * Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < colCount; ++i) {
			vshadowBottom.draw(gc, pX + i * Settings.SLOT_DEFAULT_WIDTH, newpY);
		}
		
		// Draw horizontal shadow
		for (int i = 0; i < rowCount; ++i) {
			hshadowLeft.draw(gc, pX, pY + i * Settings.SLOT_DEFAULT_WIDTH);
		}
		pX += (colCount - 1) * Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < rowCount; ++i) {
			hshadowRight.draw(gc, pX, pY + i * Settings.SLOT_DEFAULT_WIDTH);
		}
	}

	@Override
	protected void update(long dt) {

		// Draw Routine
		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT);

		// Perform calculations and image processing

		// Update objects state (e.g. obj.update(dt))
		gameModel.slotMachine.update(dt);

		// Draw entities
		gameModel.slotMachine.draw(gc);
		drawBlocks();
		drawShadow();
		drawIce();
	}

	@Override
	protected void bindKeys() {
		this.setOnKeyPressed(e -> {
			InputHandler.pressKey(e.getCode());
		});
		this.setOnKeyReleased(e -> {
			InputHandler.releaseKey(e.getCode());
		});
	}
}
