package core.game;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.InvalidAssetException;
import core.asset.sfx.MusicPlayer;
import core.model.Pricing;
import core.settings.Settings;
import javafx.scene.media.AudioClip;

public class GameInstance {

	private GameModel gameModel;
	private GameLogic gameLogic;
	private GameCanvas mainGameCanvas, statusCanvas, nameCanvas;
	private MusicPlayer bgmPlayer;

	public GameInstance() {
		// Load graphics and sounds
		try {
			AssetCache.loadAssets();
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
		Pricing.initialize();
		
		// Play BGM
		bgmPlayer = new MusicPlayer(AssetID.BGM_SFX, AudioClip.INDEFINITE);
		bgmPlayer.play();

		// Initialize game modules
		gameModel = new GameModel();
		gameLogic = new GameLogic(gameModel);

		mainGameCanvas = new MainGameCanvas(gameModel, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT);
		statusCanvas = new StatusCanvas(gameModel, Settings.STATUS_CANVAS_WIDTH, Settings.STATUS_CANVAS_HEIGHT);
		nameCanvas = new NameCanvas(gameModel, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
	}

	public void startGame() {
		gameLogic.startGame();
		mainGameCanvas.startAnimation();
		statusCanvas.startAnimation();
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public GameCanvas getMainGameCanvas() {
		return mainGameCanvas;
	}

	public GameCanvas getStatusCanvas() {
		return statusCanvas;
	}

	public GameCanvas getNameCanvas() {
		return nameCanvas;
	}

}
