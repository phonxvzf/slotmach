package core.model;

import core.asset.gfx.Sprite;
import core.asset.gfx.StaticSprite;
import core.asset.AssetID;

public enum SlotType {

	SLOT_K("K", new StaticSprite(AssetID.K_IMG)),
	SLOT_BANANA("banana", new StaticSprite(AssetID.BANANA_IMG)),
	SLOT_EGG("egg", new StaticSprite(AssetID.EGG_IMG)),
	SLOT_O("O", new StaticSprite(AssetID.O_IMG)),
	SLOT_CHERRY("cherry", new StaticSprite(AssetID.CHERRY_IMG)),
	SLOT_YOUTUBE("youtube", new StaticSprite(AssetID.YOUTUBE_IMG)),
	SLOT_ANDROID("android", new StaticSprite(AssetID.ANDROID_IMG));
//	SLOT_BEING("being", new StaticSprite(AssetID.BEING_IMG)),
//	SLOT_BOXz("box", new StaticSprite(AssetID.BOXz_IMG)),
//	SLOT_drop("dropbox", new StaticSprite(AssetID.DROP_IMG)),
//	SLOT_FB("fb", new StaticSprite(AssetID.FB_IMG)),
//	SLOT_GOOGLE("google", new StaticSprite(AssetID.GOOGLE_IMG)),
//	SLOT_HTML("html", new StaticSprite(AssetID.HTML_IMG)),
//	SLOT_IG("ig", new StaticSprite(AssetID.IG_IMG)),
//	SLOT_IN("in", new StaticSprite(AssetID.IN_IMG)),
//	SLOT_PAYPAL("paypal", new StaticSprite(AssetID.PAYPAL_IMG)),
//	SLOT_REDDIT("reddit", new StaticSprite(AssetID.REDDIT_IMG)),
//	SLOT_SKYPE("skype", new StaticSprite(AssetID.SKYPE_IMG)),
//	SLOT_TW("tw", new StaticSprite(AssetID.TW_IMG)),
//	SLOT_WIKI("wikipedia", new StaticSprite(AssetID.WIKI_IMG)),
//	SLOT_WORDPRESS("wordpress", new StaticSprite(AssetID.WORDPRESS_IMG)),
//	SLOT_YELP("yelp", new StaticSprite(AssetID.YELP_IMG)),
//	SLOT_BIRD("bird", new StaticSprite(AssetID.BIRD_IMG));
	
	private final String code;
	private final Sprite sprite;

	private SlotType(String code, Sprite sprite) {
		this.code = code;
		this.sprite = sprite;
	}

	public String getCode() {
		return code;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
