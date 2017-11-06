package core.asset;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class AssetCache {
	
	private static final Map<AssetID, Image> imageCache = new HashMap<AssetID, Image>();
	
	public static void loadAssets() {
		imageCache.put(AssetID.TEST_IMG, new Image("file:assets/test.png"));
		imageCache.put(AssetID.K_IMG, new Image("file:assets/K.png"));
		imageCache.put(AssetID.O_IMG, new Image("file:assets/O.png"));
	}
	
	public static Image getImage(AssetID id) {
		return imageCache.get(id);
	}
}