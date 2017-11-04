package core.gfx;

import javafx.scene.canvas.GraphicsContext;

public class StaticSprite extends Sprite {

	private AssetID assetID;

	public StaticSprite(GraphicsContext gc, AssetID assetID) {
		super(gc);
		this.assetID = assetID;
	}
	
	@Override
	public void draw(double x, double y) {
		graphicsContext.drawImage(AssetCache.getImage(assetID), x, y);
	}
	
}
