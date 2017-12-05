package core.asset;

public enum AssetID {

	TEST_IMG("img/test.png"),
	K_IMG("img/K.png"),
	O_IMG("img/O.png"),
	FROZEN_IMG("img/frozen.png"),
	MPBAR_IMG("img/mpbar.png"),
	box("img/box.png");
	
	private final String uri;
	private AssetID(String uri) {
		this.uri = uri;
	}
	
	public String getURI() {
		return uri;
	}
}
