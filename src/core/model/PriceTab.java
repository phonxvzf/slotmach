package core.model;

import core.asset.AssetCache;
import core.asset.InvalidAssetException;
import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class PriceTab extends Entity implements Drawable {

	private Font titleFont, textFont;
	private static final double VGAP = 30.0f;
	private static final double VOFFSET = 60.0f;
	private static final double VLINE_OFFSET = 20.0f;

	public PriceTab(double x, double y) {
		super(x, y);
		try {
			titleFont = AssetCache.loadFont("profont.ttf", 42);
			textFont = AssetCache.loadFont("profont.ttf", 28);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {

		// Draw background
		gc.setGlobalAlpha(0.8f);
		gc.setFill(Color.rgb(50, 0, 0));
		gc.fillRect(posX, posY, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT * 0.8f);
		gc.setGlobalAlpha(1.0f);
		gc.setStroke(Color.DARKRED);
		gc.setLineWidth(3);
		gc.strokeRect(posX, posY, Settings.GAME_CANVAS_WIDTH, Settings.GAME_CANVAS_HEIGHT * 0.8f);

		// Draw title
		gc.setFill(Color.RED);
		gc.setFont(titleFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.TOP);
		double midX = Settings.GAME_CANVAS_WIDTH / 2, drawY = posY + VGAP;
		gc.fillText("PRICE LIST", midX, drawY);
		drawVLine(gc, drawY += VOFFSET);

		// Price list
		gc.setFill(Color.GOLD);
		gc.fillText("- JACKPOT -", midX, drawY += VLINE_OFFSET);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setFill(Color.RED);
		SlotType.SLOT_PROGMETH.getSprite().draw(gc, midX - 25, drawY += VOFFSET - 5);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.GREENYELLOW);
		gc.fillText("+$10000", midX, drawY += VOFFSET);

		drawVLine(gc, drawY += VOFFSET);
		gc.setFont(textFont);
		gc.setFill(Color.WHITE);
		gc.fillText("- OTHERS -", midX, drawY += VLINE_OFFSET);
		gc.setTextAlign(TextAlignment.LEFT);
		drawY += VOFFSET;
		SlotType.SLOT_K.getSprite().draw(gc, midX / 2 - 75, drawY - 12.5);
		gc.fillText("+$300", midX / 2, drawY);
		SlotType.SLOT_O.getSprite().draw(gc, midX / 2 - 75, drawY + VOFFSET - 12.5);
		gc.fillText("+$300", midX / 2, drawY + VOFFSET);
		SlotType.SLOT_CHERRY.getSprite().draw(gc, midX * 1.5f - 75, drawY - 12.5);
		gc.fillText("+$100", midX * 1.5f, drawY);
		SlotType.SLOT_BANANA.getSprite().draw(gc, midX * 1.5f - 75, drawY + VOFFSET - 12.5);
		gc.fillText("+$50", midX * 1.5f, drawY + VOFFSET);

		SlotType.SLOT_K.getSprite().draw(gc, midX - 75, drawY);
		SlotType.SLOT_O.getSprite().draw(gc, midX - 25, drawY);
		SlotType.SLOT_K.getSprite().draw(gc, midX + 25, drawY);
		gc.setFont(titleFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("+$1000", midX, drawY + VOFFSET);

		drawVLine(gc, drawY += 2 * VOFFSET);

		// Draw controls
		gc.setFill(Color.RED);
		gc.setFont(titleFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText("INSTRUCTIONS", midX, drawY += VGAP);

		gc.setFont(textFont);
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("[SPACEBAR]: stop slot (-$50 each start),", midX, drawY += VOFFSET);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("[ARROW KEYS]: expand/shrink rows/columns (-$200),", midX, drawY += VOFFSET);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("[F]: frz, [K/O]: convert column to all Ks/Os (-$" + Integer.toString(Settings.PLAYER_PAID_BUYCOL)
				+ "), [ESC]: quit", midX, drawY += VOFFSET);

	}

	private void drawVLine(GraphicsContext gc, double y) {
		final double x = Settings.GAME_CANVAS_WIDTH / 10;
		final double lineWidth = Settings.GAME_CANVAS_WIDTH * 0.8f;
		gc.setStroke(Color.DARKRED);
		gc.setLineWidth(2);
		gc.strokeLine(x, y, x + lineWidth, y);
		gc.setStroke(Color.BLACK);
		gc.strokeLine(x, y + 2, x + lineWidth, y + 2);
	}

}
