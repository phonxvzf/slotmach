package core.game;

import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.settings.Settings;
import javafx.scene.paint.Color;

public class MainGameCanvas extends GameCanvas {

	private StaticSprite frozenSprite = new StaticSprite(AssetID.FROZEN_IMG);
	
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
		
		gc.setGlobalAlpha(gameModel.slotMachine.getColumns() == gameModel.slotMachine.getPullCount() 
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
