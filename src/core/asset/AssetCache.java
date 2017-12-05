package core.asset;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public final class AssetCache {

	private static final Map<AssetID, Image> imageCache = new HashMap<AssetID, Image>();

	public static void loadAssets() throws InvalidAssetException {
		for (AssetID id : AssetID.values()) {
			try {
				imageCache.put(id, new Image(id.getURI()));
			} catch (IllegalArgumentException e) {
				throw new InvalidAssetException(id.toString());
			}
		}
	}

	public static Image getImage(AssetID id) {
		if (imageCache.containsKey(id))
			return imageCache.get(id);
		return null;
	}

	public static Font loadFont(String fontName, double size) throws InvalidAssetException {
		Font ret = Font.loadFont(ClassLoader.getSystemResourceAsStream("fnt/" + fontName), size);
		if (ret == null)
			throw new InvalidAssetException();
		return ret;
	}

}
