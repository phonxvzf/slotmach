package core.game;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.InvalidAssetException;
import core.asset.gfx.StaticSprite;
import core.model.Background;
import core.model.ManaBar;
import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class StatusCanvas extends GameCanvas {

	private ManaBar freezeManaBar;
	private Font moneyFont;
	private static final double Y_OFFSET = 20;
	private Background background = new Background(new StaticSprite(AssetID.STATUSBG_IMG), 0, 0);

	public StatusCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		try {
			freezeManaBar = new ManaBar(new StaticSprite(AssetID.MPBAR_IMG), 70, 80 + Y_OFFSET, 206, 30, Settings.PLAYER_MAX_MANA,
					"frz");
			moneyFont = AssetCache.loadFont("profont.ttf", 48);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
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
		background.draw(gc);
		freezeManaBar.draw(gc);
		
		// Draw money count
		gc.setFill(Color.GREY);
		gc.setFont(moneyFont);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("$ " + getMoneyString(gameModel.gameState.getMoney(), 8), 266, 30 + Y_OFFSET);
		gc.setFill(Color.GREENYELLOW);
		gc.fillText(Integer.toString(gameModel.gameState.getMoney()), 266, 30 + Y_OFFSET);
	}

	@Override
	protected void bindKeys() {
		// Nothing to do
	}
	
	private String getMoneyString(int money, int digit) {
		String tmp = Integer.toString(money);
		String ret = "";
		for (int i = 0; i < digit - tmp.length(); ++i) ret += "0";
		ret += tmp;
		return ret;
	}

}
