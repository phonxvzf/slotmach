package core.asset.gfx;

import core.asset.AssetCache;
import core.asset.AssetID;
import javafx.scene.canvas.GraphicsContext;

public class StaticSprite extends Sprite {

	private AssetID assetID;

	public StaticSprite(GraphicsContext gc, AssetID assetID) {
		super(gc, AssetCache.getImage(assetID).getWidth(), AssetCache.getImage(assetID).getHeight());
		this.assetID = assetID;
	}
	
	@Override
	public void draw(double x, double y) {
		graphicsContext.drawImage(AssetCache.getImage(assetID), x, y);
	}
	
}
