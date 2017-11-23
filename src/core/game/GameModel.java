package core.game;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.model.SlotMachine;
import core.settings.Settings;

public class GameModel {
	
	// Declare entities here
	// ======================
	// START DECLARE ENTITIES
	// ======================
	SlotMachine slotMachine;
	// ======================
	// END DECLARE ENTITIES
	// ======================
	
	GameState gameState = new GameState();
	
	public GameModel() {
		
		StaticSprite slotMachineBg = new StaticSprite(AssetID.TEST_IMG);
		final int columns = Settings.SLOT_DEFAULT_COLUMNS;
		slotMachine = new SlotMachine(
				slotMachineBg,
				(Settings.WINDOW_WIDTH - AssetCache.getImage(AssetID.K_IMG).getWidth() * columns) / 2.0f,
				0,
				columns);
	}
}
