package core.game;

import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.settings.Settings;
import javafx.scene.paint.Color;

public class MainGameCanvas extends GameCanvas {

	private StaticSprite frozenSprite = new StaticSprite(AssetID.FROZEN_IMG);
	private StaticSprite boxExtends = new StaticSprite(AssetID.box);

	public MainGameCanvas(GameModel model, double width, double height) {
		super(model, width, height);
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
			for (int j = endColLeft + beginColLeft; j <= beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}
		int beginRowDown = endRowUp + 50 * (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow());
		for (int i = beginRowDown; i < Settings.GAME_CANVAS_HEIGHT; i += 50) {
			for (int j = endColLeft + beginColLeft; j <= beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}

		gc.setGlobalAlpha(gameModel.slotMachine.isAllStop() ? 0.0f
				: (Settings.SLOT_DEFAULT_VELOCITY
						- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
						/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY));
		
		gc.setGlobalAlpha(gameModel.slotMachine.isAllStop() 
				? 0.0f 
				: (Settings.SLOT_DEFAULT_VELOCITY 
				- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
				/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY)
				);
		frozenSprite.draw(gc, 0, 0);
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
