package core.asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public final class AssetCache {

	private static final Map<AssetID, Image> imageCache = new HashMap<AssetID, Image>();
	private static final Map<AssetID, List<Image>> imageSequenceCache = new HashMap<AssetID, List<Image>>();
	private static final Map<AssetID, AudioClip> audioCache = new HashMap<AssetID, AudioClip>();
	private static int loaded = 0;
	private static boolean isLoaded = false;
	
	private static String convertURL(String url) {
		return ClassLoader.getSystemResource(url).toString();
	}

	public static void loadAssets() throws InvalidAssetException {
		for (AssetID id : AssetID.values()) {
			if (id.getType() == AssetType.IMAGE) {
				try {
					imageCache.put(id, new Image(convertURL(id.getURL())));
				} catch (IllegalArgumentException e) {
					throw new InvalidAssetException(id.toString());
				}
			} else if (id.getType() == AssetType.AUDIO) {
				try {
					audioCache.put(id, new AudioClip(convertURL(id.getURL())));
				} catch (IllegalArgumentException e) {
					throw new InvalidAssetException(id.toString());
				}
			} else if (id.getType() == AssetType.IMAGE_SEQUENCE) {
				List<Image> frames = new ArrayList<Image>();
				for (int i = 0; i < id.getCount(); ++i) {
					try {
						frames.add(new Image(convertURL(id.getURL() + Integer.toString(i) + ".png")));
					} catch (IllegalArgumentException e) {
						throw new InvalidAssetException(id.toString() + " frame #" + i);
					}
				}
				imageSequenceCache.put(id, frames);
			} else {
				throw new InvalidAssetException("Unknown type: " + id.getType().toString());
			}
			loaded++;
		}
		isLoaded = true;
	}

	public static Image getImage(AssetID id) {
		if (imageCache.containsKey(id))
			return imageCache.get(id);
		return null;
	}

	public static List<Image> getImageSequence(AssetID id) {
		if (imageSequenceCache.containsKey(id))
			return imageSequenceCache.get(id);
		return null;
	}

	public static AudioClip getAudio(AssetID id) {
		if (audioCache.containsKey(id))
			return audioCache.get(id);
		return null;
	}

	public static Font loadFont(String fontName, double size) throws InvalidAssetException {
		Font ret = Font.loadFont(ClassLoader.getSystemResourceAsStream("fnt/" + fontName), size);
		if (ret == null)
			throw new InvalidAssetException();
		return ret;
	}
	
	public static double getLoadingProgress() {
		return ((double) loaded) / AssetID.values().length;
	}
	
	public static boolean isLoaded() {
		return isLoaded;
	}

}
