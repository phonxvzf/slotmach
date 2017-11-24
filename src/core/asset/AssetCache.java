package core.asset;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public final class AssetCache {
	
	private static final Map<AssetID, Image> imageCache = new HashMap<AssetID, Image>();
	
	public static void loadAssets() {
		imageCache.put(AssetID.TEST_IMG, new Image("file:assets/test.png"));
		imageCache.put(AssetID.K_IMG, new Image("file:assets/K.png"));
		imageCache.put(AssetID.O_IMG, new Image("file:assets/O.png"));
		imageCache.put(AssetID.FROZEN_IMG, new Image("file:assets/frozen.png"));
		imageCache.put(AssetID.MPBAR_IMG, new Image("file:assets/mpbar.png"));
	}
	
	public static Image getImage(AssetID id) {
		return imageCache.get(id);
	}
}
