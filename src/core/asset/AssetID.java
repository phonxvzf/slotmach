package core.asset;

public enum AssetID {

	// Images
	TEST_IMG("img/test.png", AssetType.IMAGE),
	K_IMG("img/K.png", AssetType.IMAGE),
	O_IMG("img/O.png", AssetType.IMAGE),
	FROZEN_IMG("img/frozen.png", AssetType.IMAGE),
	MPBAR_IMG("img/mpbar.png", AssetType.IMAGE),
	box("img/box.png", AssetType.IMAGE),
	
	// Sounds
	BGM_SFX("sfx/bgm.wav", AssetType.AUDIO),
	BLIP_SFX("sfx/blip2.wav", AssetType.AUDIO),
	FREEZE_SFX("sfx/freeze.mp3", AssetType.AUDIO);
	
	enum AssetType {
		IMAGE,
		AUDIO
	}
	
	private final String url;
	private final AssetType type;
	private AssetID(String url, AssetType type) {
		this.url = url;
		this.type = type;
	}
	
	public String getURL() {
		return ClassLoader.getSystemResource(url).toString();
	}
	
	public AssetType getType() {
		return type;
	}
}
