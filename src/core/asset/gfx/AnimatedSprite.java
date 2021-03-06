package core.asset.gfx;

import java.util.ArrayList;
import java.util.List;

import core.asset.AssetCache;
import core.asset.AssetID;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedSprite extends Sprite {

	private List<Image> frames = new ArrayList<Image>();
	private long startTime = System.currentTimeMillis();
	private int spriteID = 0;
	private long timeToLive = 500; // in milliseconds
	
	public AnimatedSprite(List<AssetID> assetIDs) {
		super(AssetCache.getImage(assetIDs.get(0)).getWidth(), AssetCache.getImage(assetIDs.get(0)).getHeight());
		for (AssetID id : assetIDs) {
			frames.add(AssetCache.getImage(id));
		}
	}
	
	public AnimatedSprite(AssetID id) {
		super(AssetCache.getImageSequence(id).get(0).getWidth(), AssetCache.getImageSequence(id).get(0).getHeight());
		frames = AssetCache.getImageSequence(id);
	}
	
	@Override
	public void draw(GraphicsContext gc, double x, double y) {
		long now = System.currentTimeMillis();
		if (timeToLive > 0) {
			if (now - startTime >= timeToLive) {
				startTime = now;
				spriteID = (spriteID + 1) % frames.size();
			}
		}
		gc.drawImage(frames.get(spriteID), x, y);
	}

	public void setTTL(int ttl) {
		this.timeToLive = ttl;
		this.startTime = System.currentTimeMillis();
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
	
	public int getFrameID() {
		return spriteID;
	}
	
}
