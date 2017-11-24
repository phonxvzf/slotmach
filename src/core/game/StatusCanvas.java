package core.game;

import core.settings.Settings;
import javafx.scene.paint.Color;

public class StatusCanvas extends GameCanvas {

	public StatusCanvas(GameModel model, double width, double height) {
		super(model, width, height);
	}

	@Override
	protected void update(long dt) {
		
		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.STATUS_CANVAS_WIDTH, Settings.STATUS_CANVAS_HEIGHT);
		gc.setFill(Color.AQUA);
		gc.fillRect(0, 0, Settings.STATUS_CANVAS_WIDTH, Settings.STATUS_CANVAS_HEIGHT);
	
	}

	@Override
	protected void bindKeys() {
		
	}

}
