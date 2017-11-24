package core.game;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import core.settings.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatusCanvas extends GameCanvas {
	private static final Font font=new Font("optima", 20);
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
		gc.setFill(Color.BLACK);
		gc.setFont(font);
		gc.fillText("MP", 20, 30);
		mpBar();
	}

	@Override
	protected void bindKeys() {

	}

	protected void mpBar() {
		gc.setFill(Color.BLACK);
		gc.fillRect(60, 10, 210	, 30);
		gc.setFill(Color.RED);
		gc.fillRect(60, 10,	this.gameModel.gameState.getMana(), 30);
	}

}
