package core.asset.gfx;

import core.asset.AssetCache;
import core.asset.AssetID;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StaticSprite extends Sprite {

	private Image image;

	public StaticSprite(AssetID assetID) {
		super(AssetCache.getImage(assetID).getWidth(), AssetCache.getImage(assetID).getHeight());
		this.image = AssetCache.getImage(assetID);
	}
	
	@Override
	public void draw(GraphicsContext gc, double x, double y) {
		gc.drawImage(image, x, y);
	}
	
	public Image getImage() {
		return image;
	}
	
}
