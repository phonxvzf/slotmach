package core.game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private Font moneyFont, multFont, boardFont;
	private static final double Y_OFFSET = 80;
	private Background background = new Background(new StaticSprite(AssetID.STATUSBG_IMG), 0, 0);
	private List<Entry<String, Integer>> scoreList = null;

	public StatusCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		try {
			freezeManaBar = new ManaBar(new StaticSprite(AssetID.MPBAR_IMG), 70, 340, 206, 30, Settings.PLAYER_MAX_MANA,
					"frz");
			moneyFont = AssetCache.loadFont("profont.ttf", 48);
			multFont = AssetCache.loadFont("profont.ttf", 30);
			boardFont = AssetCache.loadFont("profont.ttf", 24);
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
		background.draw(gc);

		// Show name
		gc.setFont(multFont);
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.fillText(gameModel.gameState.getName(), 266, 30);
		gc.setStroke(Color.DARKRED);
		gc.setLineWidth(2);
		gc.strokeLine(30, 70, 266, 70);
		gc.setStroke(Color.BLACK);
		gc.strokeLine(30, 73, 266, 73);

		// Show cash amount
		gc.setFill(Color.GREY);
		gc.setFont(moneyFont);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("$ " + getMoneyString(gameModel.gameState.getMoney(), 8), 266, 30 + Y_OFFSET);
		if (gameModel.gameState.getMoney() >= gameModel.gameState.getHighScore())
			gc.setFill(Color.GREENYELLOW);
		else
			gc.setFill(Color.RED);
		gc.fillText(Integer.toString(gameModel.gameState.getMoney()), 266, 30 + Y_OFFSET);

		// Show payout
		gc.setFont(multFont);
		gc.setFill(Color.CORAL);
		gc.fillText("DIFF", 266, 70 + Y_OFFSET);
		if (gameModel.gameState.getPayout() > 0) {
			gc.setFill(Color.GREENYELLOW);
			gc.fillText("+" + Integer.toString(gameModel.gameState.getPayout()), 266, 100 + Y_OFFSET);
		} else if (gameModel.gameState.getPayout() == 0) {
			gc.setFill(Color.GREY);
			gc.fillText(Integer.toString(gameModel.gameState.getPayout()), 266, 100 + Y_OFFSET);
		} else {
			gc.setFill(Color.RED);
			gc.fillText(Integer.toString(gameModel.gameState.getPayout()), 266, 100 + Y_OFFSET);
		}

		// Show multiplier
		double multiplier = gameModel.gameState.getColMultiplier() * gameModel.gameState.getRowMultiplier();
		if (multiplier < 1.0f)
			gc.setFill(Color.CRIMSON);
		else
			gc.setFill(Color.YELLOWGREEN);
		gc.fillText(String.format("(x%.2f)", multiplier), 266, 130 + Y_OFFSET);

		// Show high score
		gc.setFill(Color.CORAL);
		gc.fillText("HIGH", 266, 170 + Y_OFFSET);
		gc.setFill(Color.GREY);
		gc.fillText(Integer.toString(gameModel.gameState.getHighScore()), 266, 200 + Y_OFFSET);

		gc.setStroke(Color.DARKRED);
		gc.setLineWidth(2);
		gc.strokeLine(30, 230 + Y_OFFSET, 266, 230 + Y_OFFSET);
		gc.setStroke(Color.BLACK);
		gc.strokeLine(30, 233 + Y_OFFSET, 266, 233 + Y_OFFSET);

		// Update entities
		freezeManaBar.setColor(150, 0, 0);
		freezeManaBar.setAmount(gameModel.gameState.getMana());

		// Draw entities
		freezeManaBar.draw(gc);

		// Show scoreBoard
		scoreBoardUpdate();
	}

	@Override
	protected void bindKeys() {
		// Nothing to do
	}

	private void scoreBoardUpdate() {
		gameModel.gameState.updateScore();
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setFont(multFont);
		gc.setFill(Color.WHITE);
		double y = 400;
		gc.fillText("SCORE BOARD", 266, y);

		gc.setStroke(Color.DARKRED);
		gc.setLineWidth(2);
		gc.strokeLine(30, y + 40, 266, y + 40);
		gc.setStroke(Color.BLACK);
		gc.strokeLine(30, y + 3 + 40, 266, y + 3 + 40);

		gc.setFont(boardFont);

		Stream<Map.Entry<String, Integer>> sorted = gameModel.gameState.getScore().entrySet().stream()
				.sorted(Map.Entry.comparingByValue());
		scoreList = sorted.collect(Collectors.toList());
		int i = 1;
		y += 20;
		for (Entry<String, Integer> key : scoreList) {
			gc.setTextAlign(TextAlignment.LEFT);
			if (key.getKey().equals(gameModel.gameState.getName()))
				gc.setFill(Color.CORAL);
			else
				gc.setFill(Color.BISQUE);
			gc.fillText(key.getKey(), 20, y + i * 40);
			gc.setTextAlign(TextAlignment.RIGHT);
			gc.setFill(Color.GREY);
			gc.fillText(Integer.toString((gameModel.gameState.getScore().get(key.getKey()) * -1)), 266, y + i * 40);
			if (++i > 12)
				break;
		}
	}

	private String getMoneyString(int money, int digit) {
		String tmp = Integer.toString(money);
		String ret = "";
		for (int i = 0; i < digit - tmp.length(); ++i)
			ret += "0";
		ret += tmp;
		return ret;
	}

}
