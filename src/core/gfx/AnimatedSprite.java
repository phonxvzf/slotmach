package core.gfx;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class AnimatedSprite extends Sprite {

	private List<AssetID> frames = new ArrayList<AssetID>();
	private long startTime = System.currentTimeMillis();
	private int spriteID = 0;
	private long timeToLive = 500; // in ms
	
	public AnimatedSprite(GraphicsContext gc, ArrayList<AssetID> imageIDs) {
		super(gc);
		this.frames = imageIDs;
	}
	
	@Override
	public void draw(double x, double y) {
		long now = System.currentTimeMillis();
		if (now - startTime >= timeToLive) {
			startTime = now;
			spriteID = (spriteID + 1) % frames.size();
		}
		graphicsContext.drawImage(AssetCache.getImage(frames.get(spriteID)), x, y);
	}

	public void setTTL(int ttl) {
		this.timeToLive = ttl;
	}
	
	public void resetFrame() {
		spriteID = 0;
		startTime = System.currentTimeMillis();
	}
	
	public void skipFrame(int count) {
		spriteID = (spriteID + count) % frames.size();
		startTime = System.currentTimeMillis();
	}
	
	public void skipBackFrame(int count) {
		spriteID = (((spriteID - count) % frames.size()) + frames.size()) % frames.size();
		startTime = System.currentTimeMillis();
	}
	
}
