package core.asset;


public enum AssetID {

	// Images
	TEST_IMG("img/test.png", AssetType.IMAGE),
	K_IMG("img/slot_K.png", AssetType.IMAGE),
	O_IMG("img/slot_O.png", AssetType.IMAGE),
	BANANA_IMG("img/slot_banana.png", AssetType.IMAGE),
	EGG_IMG("img/slot_egg.png", AssetType.IMAGE),
	BIRD_IMG("img/slot_bird.png", AssetType.IMAGE),
	CHERRY_IMG("img/slot_cherry.png", AssetType.IMAGE),
	
	YOUTUBE_IMG("img/slot_youtube.png", AssetType.IMAGE),
	ANDROID_IMG("img/slot_android.png", AssetType.IMAGE),
	BEING_IMG("img/slot_being.png", AssetType.IMAGE),
	BOXz_IMG("img/slot_box.png", AssetType.IMAGE),
	DROP_IMG("img/slot_dropbox.png", AssetType.IMAGE),
	FB_IMG("img/slot_fb.png", AssetType.IMAGE),
	GOOGLE_IMG("img/slot_google.png", AssetType.IMAGE),
	HTML_IMG("img/slot_html.png", AssetType.IMAGE),
	IG_IMG("img/slot_ig.png", AssetType.IMAGE),
	IN_IMG("img/slot_in.png", AssetType.IMAGE),
	PAYPAL_IMG("img/slot_paypal.png", AssetType.IMAGE),
	REDDIT_IMG("img/slot_reddit.png", AssetType.IMAGE),
	SKYPE_IMG("img/slot_skype.png", AssetType.IMAGE),
	TW_IMG("img/slot_tw.png", AssetType.IMAGE),
	WIKI_IMG("img/slot_wikipedia.png", AssetType.IMAGE),
	WORDPRESS_IMG("img/slot_wordpress.png", AssetType.IMAGE),
	YELP_IMG("img/slot_yelp.png", AssetType.IMAGE),
	PROGMETH_IMG("img/slot_progmeth.png", AssetType.IMAGE),
	
	FROZEN_IMG("img/frozen.png", AssetType.IMAGE),
	MPBAR_IMG("img/mpbar.png", AssetType.IMAGE),
	BOX_IMG("img/box.png", AssetType.IMAGE),
	VSHADOW_TOP_IMG("img/vshadow_top.png", AssetType.IMAGE),
	VSHADOW_BOTTOM_IMG("img/vshadow_bottom.png", AssetType.IMAGE),
	HSHADOW_LEFT_IMG("img/hshadow_left.png", AssetType.IMAGE),
	HSHADOW_RIGHT_IMG("img/hshadow_right.png", AssetType.IMAGE),
	LIGHTX_IMG("img/lightx.png", AssetType.IMAGE),
	LIGHT0_IMG("img/light0.png", AssetType.IMAGE),
	LIGHT1_IMG("img/light1.png", AssetType.IMAGE),
	LIGHT2_IMG("img/light2.png", AssetType.IMAGE),
	STATUSBG_IMG("img/statusbg.png", AssetType.IMAGE),
	
	// Image sequences
	MLGWOW_IMGSEQ("img/wow/", AssetType.IMAGE_SEQUENCE, 107),
<<<<<<< HEAD
	JACKPOT_IMG("img/jackpot/", AssetType.IMAGE_SEQUENCE,13),
=======
	JACKPOT_IMG("img/jackpot/", AssetType.IMAGE_SEQUENCE,1),
>>>>>>> upstream/master
	MLGFROG_IMGSEQ("img/frog/", AssetType.IMAGE_SEQUENCE, 10),
	
	// Sounds
	BGM_SFX("sfx/bgm.wav", AssetType.AUDIO),
	JACKPOT_SFX("sfx/jackpot.mp3", AssetType.AUDIO),
	BLIP_SFX("sfx/blip2.wav", AssetType.AUDIO),
	FREEZE_SFX("sfx/freeze.mp3", AssetType.AUDIO),
	YAY_SFX("sfx/yay.mp3", AssetType.AUDIO),
	CASH_SFX("sfx/cash.mp3", AssetType.AUDIO);
	
	private final String url;
	private final AssetType type;
	private final int count;
	private AssetID(String url, AssetType type) {
		this.url = url;
		this.type = type;
		this.count = 1;
	}

	private AssetID(String url, AssetType type, int count) {
		this.url = url;
		this.type = type;
		this.count = count;
	}
	
	public String getURL() {
		return url;
	}
	
	public AssetType getType() {
		return type;
	}
	
	public int getCount() {
		return count;
	}
}
