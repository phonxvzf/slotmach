package core.asset.gfx;

import core.asset.AssetCache;
import core.asset.AssetID;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StaticSprite extends Sprite {

	private Image image;

	public StaticSprite(GraphicsContext gc, AssetID assetID) {
		super(gc, AssetCache.getImage(assetID).getWidth(), AssetCache.getImage(assetID).getHeight());
		this.image = AssetCache.getImage(assetID);
	}
	
	@Override
	public void draw(double x, double y) {
		graphicsContext.drawImage(image, x, y);
	}
	
}
