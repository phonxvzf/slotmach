package core.game;

import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.model.ManaBar;
import core.settings.Settings;
import javafx.scene.paint.Color;

public class StatusCanvas extends GameCanvas {
	
	private ManaBar freezeManaBar; 
	
	public StatusCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		freezeManaBar = new ManaBar(new StaticSprite(AssetID.MPBAR_IMG), 60, 10, 206, 30, Settings.PLAYER_MAX_MANA, "FRZ");
	}

	@Override
	protected void update(long dt) {

		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.STATUS_CANVAS_WIDTH, Settings.STATUS_CANVAS_HEIGHT);
		gc.setFill(Color.BISQUE);
		gc.fillRect(0, 0, Settings.STATUS_CANVAS_WIDTH, Settings.STATUS_CANVAS_HEIGHT);
		
		// Update entities
		freezeManaBar.setColor(100, 150, 255);
		freezeManaBar.setAmount(gameModel.gameState.getMana());

		// Draw entities
		freezeManaBar.draw(gc);
	}

	@Override
	protected void bindKeys() {
		// Nothing to do
	}

}
