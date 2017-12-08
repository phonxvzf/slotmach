package core.game;

import core.asset.AssetCache;
import core.asset.InvalidAssetException;
import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameOverCanvas extends GameCanvas {

	private Font titleFont, textFont;

	public GameOverCanvas(GameModel model, double width, double height) {
		super(model, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		try {
			titleFont = AssetCache.loadFont("profont.ttf", 40);
			textFont = AssetCache.loadFont("profont.ttf", 30);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
	}

	@Override
	protected void update(long dt) {
		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		gc.setFont(titleFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.RED);
		double midX = this.getWidth() / 2, midY = this.getHeight() / 2;
		gc.fillText("YOU LOSE", midX, midY - 60);
		gc.setFont(textFont);
		gc.fillText("You deserve it!", midX, midY);
		gc.setFill(Color.WHITE);
		gc.fillText("Hit enter to restart", midX, midY + 45);
	}

	@Override
	protected void bindKeys() {
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				this.stopAnimation();
				SceneManager.gotoNameInput();
			}
		});
	}

}
