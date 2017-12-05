package core.game;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.InvalidAssetException;
import core.asset.sfx.MusicPlayer;
import core.settings.Settings;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class NameCanvas extends GameCanvas {

	private Font bannerFont;
	private Font textFont;

	public NameCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		try {
			bannerFont = AssetCache.loadFont(Settings.GAME_FONT, 32);
			textFont = AssetCache.loadFont(Settings.GAME_FONT, 26);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
	}

	@Override
	protected void update(long dt) {

		handleInput();

		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

		// Draw components
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.RED);

		double slotmachX = Settings.WINDOW_WIDTH / 2, slotmachY = Settings.WINDOW_HEIGHT / 2 - 80;
		gc.setFont(bannerFont);
		gc.fillText("slotmach", slotmachX, slotmachY);

		gc.setFont(textFont);
		gc.setFill(Color.WHITE);
		gc.fillText("enter-your-name", slotmachX, slotmachY + 30);

		gc.setFont(bannerFont);
		gc.fillText(gameModel.gameState.getName(), slotmachX, slotmachY + 120);

		// Draw name box
		gc.setStroke(Color.RED);
		gc.setLineWidth(3);
		gc.strokeRect(slotmachX - 111.75, slotmachY + 76, 228, 60);
	}

	@Override
	protected void bindKeys() {
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (gameModel.gameState.name.length() > 0) {
					new MusicPlayer(AssetID.BLIP_SFX, 1).play();
					SceneManager.gotoGameScene();
					this.stopAnimation();
				}
			} else if (e.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
				System.exit(0);
			} else {
				InputHandler.pressKey(e.getCode());
			}
		});
		this.setOnKeyReleased(e -> {
			InputHandler.releaseKey(e.getCode());
		});
	}

	private void handleInput() {
		KeyCode triggeredKey;
		while ((triggeredKey = InputHandler.pollTriggeredKey()) != null) {
			if (triggeredKey == KeyCode.BACK_SPACE) {
				if (gameModel.gameState.name.length() > 0) {
					gameModel.gameState.name = gameModel.gameState.name.substring(0,
							gameModel.gameState.name.length() - 1);
				}
			} else if (gameModel.gameState.name.length() < Settings.PLAYER_MAX_NAME_LENGTH) {
				String text = triggeredKey.toString();
				if (text == "MINUS") {
					gameModel.gameState.name += "-";
				} else if (text.length() == 1 && Character.isLetter(text.charAt(0))) {
					gameModel.gameState.name += text.toLowerCase();
				}
			}
		}
	}

}
