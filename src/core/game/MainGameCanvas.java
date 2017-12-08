package core.game;

import java.util.ArrayList;
import java.util.List;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.InvalidAssetException;
import core.asset.gfx.AnimatedSprite;
import core.asset.gfx.StaticSprite;
import core.model.LightBox;
import core.model.PriceTab;
import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainGameCanvas extends GameCanvas {

	private StaticSprite frozenSprite = new StaticSprite(AssetID.FROZEN_IMG);
	private StaticSprite boxExtends = new StaticSprite(AssetID.BOX_IMG);
	private StaticSprite vshadowTop = new StaticSprite(AssetID.VSHADOW_TOP_IMG);
	private StaticSprite vshadowBottom = new StaticSprite(AssetID.VSHADOW_BOTTOM_IMG);
	private StaticSprite hshadowLeft = new StaticSprite(AssetID.HSHADOW_LEFT_IMG);
	private StaticSprite hshadowRight = new StaticSprite(AssetID.HSHADOW_RIGHT_IMG);

	private AnimatedSprite mlgWow = new AnimatedSprite(AssetID.MLGWOW_IMGSEQ);
	private AnimatedSprite mlgFrog = new AnimatedSprite(AssetID.MLGFROG_IMGSEQ);
	private AnimatedSprite mlgFrogRev = new AnimatedSprite(AssetID.MLGFROG_REV_IMGSEQ);
	private AnimatedSprite mlgRapper = new AnimatedSprite(AssetID.MLGRAPPER_IMGSEQ);

	private List<LightBox> leftLights = new ArrayList<>();
	private List<LightBox> rightLights = new ArrayList<>();

	private PriceTab priceTab = new PriceTab(0, Settings.GAME_CANVAS_HEIGHT / 10);

	private Font jackpotFont;

	public MainGameCanvas(GameModel model, double width, double height) {
		super(model, width, height);
		int rowCount = (int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH);
		double pX = gameModel.slotMachine.getSlotColumn(0).getPosX() - Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < rowCount; ++i) {
			leftLights.add(new LightBox(pX, i * Settings.SLOT_DEFAULT_WIDTH));
		}
		pX += (Settings.SLOT_DEFAULT_COLUMNS + 1) * Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < rowCount; ++i) {
			rightLights.add(new LightBox(pX, i * Settings.SLOT_DEFAULT_WIDTH));
		}
		mlgWow.setTTL(15);
		mlgFrog.setTTL(43);
		mlgFrogRev.setTTL(43);
		mlgRapper.setTTL(50);

		try {
			jackpotFont = AssetCache.loadFont("profont.ttf", 180);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}

	}

	private void drawBlocks() {
		int beginColLeft = (int) ((Settings.GAME_CANVAS_WIDTH - Settings.SLOT_DEFAULT_COLUMNS * 50) / 2);
		int endColLeft = 50 * (Settings.SLOT_DEFAULT_COLUMNS / 2 - Settings.SLOT_DEFAULT_BEGIN_COLUMNS / 2
				- gameModel.slotMachine.getAddlerColumns() / 2);
		int beginColRight = beginColLeft + endColLeft
				+ 50 * (Settings.SLOT_DEFAULT_BEGIN_COLUMNS + gameModel.slotMachine.getAddlerColumns());
		for (int j = 0; j < Settings.GAME_CANVAS_HEIGHT; j += 50) {
			for (int i = beginColLeft; i < endColLeft + beginColLeft; i += 50) {
				boxExtends.draw(gc, i, j);
			}
			for (int i = beginColRight; i < endColLeft + beginColRight; i += 50) {
				boxExtends.draw(gc, i, j);
			}
		}
		int endRowUp = 50
				* (Settings.SLOT_DEFAULT_ROWS - Settings.SLOT_DEFAULT_BEGIN_ROWS - gameModel.slotMachine.getAddlerRow())
				/ 2;
		for (int i = 0; i < endRowUp; i += 50) {
			for (int j = endColLeft + beginColLeft; j < beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}
		int beginRowDown = endRowUp + 50 * (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow());
		for (int i = beginRowDown; i < Settings.GAME_CANVAS_HEIGHT; i += 50) {
			for (int j = endColLeft + beginColLeft; j < beginColRight; j += 50) {
				boxExtends.draw(gc, j, i);
			}
		}
	}

	private void drawIce() {
		gc.setGlobalAlpha(gameModel.slotMachine.isAllStop() ? 0.0f
				: (Settings.SLOT_DEFAULT_VELOCITY
						- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
						/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY));
		frozenSprite.draw(gc, 0, 0);
		gc.setGlobalAlpha(1.0f);
	}

	private void drawShadow() {
		int startRow = ((int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH)
				- (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow())) / 2;
		int startCol = (Settings.SLOT_DEFAULT_COLUMNS / 2 - 1) - gameModel.slotMachine.getAddlerColumns() / 2;
		int rowCount = Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow();
		int colCount = Settings.SLOT_DEFAULT_BEGIN_COLUMNS + gameModel.slotMachine.getAddlerColumns();

		double pX = (Settings.GAME_CANVAS_WIDTH - (Settings.SLOT_DEFAULT_COLUMNS * Settings.SLOT_DEFAULT_WIDTH)) / 2
				+ startCol * Settings.SLOT_DEFAULT_WIDTH;
		double pY = startRow * Settings.SLOT_DEFAULT_WIDTH;

		// Draw vertical shadow
		for (int i = 0; i < colCount; ++i) {
			vshadowTop.draw(gc, pX + i * Settings.SLOT_DEFAULT_WIDTH, pY);
		}
		double newpY = pY + (rowCount - 1) * Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < colCount; ++i) {
			vshadowBottom.draw(gc, pX + i * Settings.SLOT_DEFAULT_WIDTH, newpY);
		}

		// Draw horizontal shadow
		for (int i = 0; i < rowCount; ++i) {
			hshadowLeft.draw(gc, pX, pY + i * Settings.SLOT_DEFAULT_WIDTH);
		}
		pX += (colCount - 1) * Settings.SLOT_DEFAULT_WIDTH;
		for (int i = 0; i < rowCount; ++i) {
			hshadowRight.draw(gc, pX, pY + i * Settings.SLOT_DEFAULT_WIDTH);
		}
	}

	private void drawLights() {
		for (int i = 0; i < leftLights.size(); ++i) {
			if (!gameModel.gameState.isMatchRow(i)) {
				leftLights.get(i).turnOff();
				rightLights.get(i).turnOff();
			} else {
				leftLights.get(i).turnOn();
				rightLights.get(i).turnOn();
			}
			leftLights.get(i).draw(gc);
			rightLights.get(i).draw(gc);
		}
	}

	private void drawPrizeAnimations() {
		if (gameModel.gameState.isJackpot() && !gameModel.gameState.isCanPull()
				&& gameModel.gameState.getPayout() > 0) {
			
			gc.setFont(jackpotFont);
			gc.setFill(Color.rgb(100, 255, (int) (255 * Math.abs(Math.sin(System.nanoTime() / 1e9 * 10)))));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.CENTER);
			gc.setLineWidth(3);
			gc.fillText("JACKPOT", this.getWidth() / 2, this.getHeight() / 4);
			gc.strokeText("JACKPOT", this.getWidth() / 2, this.getHeight() / 4);
			mlgFrog.draw(gc, this.getWidth() - mlgFrog.getHeight(), this.getHeight() - mlgFrog.getHeight());
			mlgFrogRev.draw(gc, 0, this.getHeight() - mlgFrog.getHeight());
			mlgRapper.draw(gc, (this.getWidth() - mlgRapper.getWidth()) / 2, this.getHeight() - mlgRapper.getHeight());
			gameModel.gameState.setJackpot(true);
		} else {
			mlgFrog.resetFrame();
			mlgFrogRev.resetFrame();
			mlgRapper.resetFrame();
		}
		if (!gameModel.gameState.isJackpot() && !gameModel.gameState.isCanPull()
				&& gameModel.gameState.getPayout() > 0) {
			mlgWow.draw(gc, (Settings.GAME_CANVAS_WIDTH - mlgWow.getWidth()) / 2,
					Settings.GAME_CANVAS_HEIGHT - mlgWow.getHeight());
		} else {
			mlgWow.resetFrame();
		}
		if (gameModel.gameState.isShowPriceTab()) {
			priceTab.draw(gc);
		}
	}

	@Override
	protected void update(long dt) {

		// Draw Routine
		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT);

		// Update objects state (e.g. obj.update(dt))
		gameModel.slotMachine.update(dt);

		// Draw entities
		gameModel.slotMachine.draw(gc);
		drawBlocks();
		drawShadow();
		drawLights();
		drawIce();
		drawPrizeAnimations();
	}

	@Override
	protected void bindKeys() {
		this.setOnKeyPressed(e -> {
			InputHandler.pressKey(e.getCode());
		});
		this.setOnKeyReleased(e -> {
			InputHandler.releaseKey(e.getCode());
		});
	}
}
