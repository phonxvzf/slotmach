package core.model;

public enum SlotType {

	SLOT_K("slot_K"),
	SLOT_BANANA("slot_banana"),
	SLOT_EGG("slot_egg"),
	SLOT_O("slot_O"),
	SLOT_CHERRY("slot_cherry"),
	SLOT_YOUTUBE("slot_youtube"),
	SLOT_ANDROID("slot_android"),
	SLOT_BEING("slot_being"),
	SLOT_BOXz("slot_box"),
	SLOT_drop("slot_dropbox"),
	SLOT_FB("slot_fb"),
	SLOT_GOOGLE("slot_google"),
	SLOT_HTML("slot_html"),
	SLOT_IG("slot_ig"),
	SLOT_IN("slot_in"),
	SLOT_PAYPAL("slot_paypal"),
	SLOT_REDDIT("slot_reddit"),
	SLOT_SKYPE("slot_skype"),
	SLOT_TW("slot_tw"),
	SLOT_WIKI("slot_wikipedia"),
	SLOT_WORDPRESS("slot_wordpress"),
	SLOT_YELP("slot_yelp"),
	SLOT_BIRD("slot_bird");
	
	private final String code;

	private SlotType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
