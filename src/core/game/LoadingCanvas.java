package core.game;

import core.asset.AssetCache;
import core.model.LoadingBar;
import javafx.scene.paint.Color;

public class LoadingCanvas extends GameCanvas {

	private LoadingBar loadingBar;

	public LoadingCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		loadingBar = new LoadingBar(width / 2, height / 2, 400, 10);
	}

	@Override
	protected void update(long dt) {
		// Clear all
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		loadingBar.setProgress(AssetCache.getLoadingProgress());
		loadingBar.draw(gc);
	}

	@Override
	protected void bindKeys() {
		// No keys is bound
	}

}
