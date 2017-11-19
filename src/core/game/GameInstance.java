package core.game;

import core.asset.AssetCache;

public class GameInstance {
	
	private static GameModel gameModel;
	private static GameLogic gameLogic;
	private static GameCanvas gameCanvas;
	
	public GameInstance() {
		// Load graphics and sounds
		AssetCache.loadAssets();
		
		// Initialize game modules
		gameModel = new GameModel();
		gameLogic = new GameLogic(gameModel);
		gameCanvas = new GameCanvas(gameModel);
	}
	
	public void startGame() {
		gameLogic.startGame();
		gameCanvas.startAnimation();
	}
	
	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}
	
}
