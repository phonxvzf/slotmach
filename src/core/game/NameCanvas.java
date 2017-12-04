package core.game;

import com.sun.javafx.tk.Toolkit;

import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class NameCanvas extends GameCanvas {
	
	private Font font = new Font(Settings.GAME_FONT, 20);
	private double textWidth;

	public NameCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth("slotmach", font);
	}

	@Override
	protected void update(long dt) {

		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Text
		gc.setFont(font);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.WHITE);
		gc.fillText("slotmach", (Settings.WINDOW_WIDTH - textWidth) / 2, Settings.WINDOW_HEIGHT / 2);
	}

	@Override
	protected void bindKeys() {
		// TODO Auto-generated method stub
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				SceneManager.gotoGameScene();
				this.stopAnimation();
			}
		});
	}

}
