package core.model;

import core.asset.AssetCache;
import core.asset.InvalidAssetException;
import core.settings.Settings;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class LoadingBar extends Entity implements Drawable {
	
	private double progress = 0.0f;
	private double width, height;
	private Font loadingFont;

	public LoadingBar(double x, double y, double w, double h) {
		super(x, y);
		width = w;
		height = h;
		try {
			loadingFont = AssetCache.loadFont(Settings.GAME_FONT, 20);
		} catch (InvalidAssetException e) {
			e.showAlertAndExit();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFont(loadingFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.WHITE);
		gc.fillText("Loading", posX, posY);
		gc.setFill(Color.RED);
		gc.fillRect(posX - width / 2, posY + 60, width * progress, height);
		gc.setLineWidth(1);
		gc.setStroke(Color.WHITE);
		gc.strokeRect(posX - width / 2, posY + 60, 400, height);
		gc.setFill(Color.WHITE);
		gc.fillText(String.format("%.2f%%", progress * 100), posX, posY + height + 30);
	}
	
	public void setProgress(double p) {
		progress = p;
	}

}
