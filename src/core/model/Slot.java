package core.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.asset.AssetID;
import core.asset.gfx.Sprite;
import core.asset.gfx.StaticSprite;
import javafx.scene.canvas.GraphicsContext;

public class Slot extends Entity implements Drawable {
	
	public static final List<SlotType> SLOT_TYPES = new ArrayList<SlotType>(
			Arrays.asList(
					SlotType.SLOT_K,
					SlotType.SLOT_BANANA,
					SlotType.SLOT_EGG,
					SlotType.SLOT_YOUTUBE,
					SlotType.SLOT_BIRD,
					SlotType.SLOT_CHERRY,
					SlotType.SLOT_ANDROID,
					SlotType.SLOT_BEING,
					SlotType.SLOT_BOXz,
					SlotType.SLOT_drop,
					SlotType.SLOT_FB,
					SlotType.SLOT_GOOGLE,
					SlotType.SLOT_HTML,
					SlotType.SLOT_IG,
					SlotType.SLOT_IN,
					SlotType.SLOT_PAYPAL,
					SlotType.SLOT_REDDIT,
					SlotType.SLOT_SKYPE,
					SlotType.SLOT_TW,
					SlotType.SLOT_WIKI,
					SlotType.SLOT_WORDPRESS,
					SlotType.SLOT_YELP,
					SlotType.SLOT_O
			)
			);
	
	private SlotType slotType;
	private Map<SlotType, Sprite> spriteMap = new HashMap<SlotType, Sprite>();
	public Slot(SlotType type, double x, double y) {
		super(x, y);
		this.setSlotType(type);
		spriteMap.put(SlotType.SLOT_K, new StaticSprite(AssetID.K_IMG));
		spriteMap.put(SlotType.SLOT_O, new StaticSprite(AssetID.O_IMG));
		spriteMap.put(SlotType.SLOT_BANANA, new StaticSprite(AssetID.BANANA_IMG));
		spriteMap.put(SlotType.SLOT_EGG, new StaticSprite(AssetID.EGG_IMG));
		spriteMap.put(SlotType.SLOT_CHERRY, new StaticSprite(AssetID.CHERRY_IMG));
		spriteMap.put(SlotType.SLOT_YOUTUBE, new StaticSprite(AssetID.YOUTUBE_IMG));
		spriteMap.put(SlotType.SLOT_BIRD, new StaticSprite(AssetID.BIRD_IMG));
		spriteMap.put(SlotType.SLOT_ANDROID, new StaticSprite(AssetID.ANDROID_IMG));
		spriteMap.put(SlotType.SLOT_BEING, new StaticSprite(AssetID.BEING_IMG));
		spriteMap.put(SlotType.SLOT_BOXz, new StaticSprite(AssetID.BOXz_IMG));
		spriteMap.put(SlotType.SLOT_drop, new StaticSprite(AssetID.DROP_IMG));
		spriteMap.put(SlotType.SLOT_FB, new StaticSprite(AssetID.FB_IMG));
		spriteMap.put(SlotType.SLOT_GOOGLE, new StaticSprite(AssetID.GOOGLE_IMG));
		spriteMap.put(SlotType.SLOT_HTML, new StaticSprite(AssetID.HTML_IMG));
		spriteMap.put(SlotType.SLOT_IG, new StaticSprite(AssetID.IG_IMG));
		spriteMap.put(SlotType.SLOT_IN, new StaticSprite(AssetID.IN_IMG));
		spriteMap.put(SlotType.SLOT_PAYPAL, new StaticSprite(AssetID.PAYPAL_IMG));
		spriteMap.put(SlotType.SLOT_REDDIT, new StaticSprite(AssetID.REDDIT_IMG));
		spriteMap.put(SlotType.SLOT_SKYPE, new StaticSprite(AssetID.SKYPE_IMG));
		spriteMap.put(SlotType.SLOT_TW, new StaticSprite(AssetID.TW_IMG));
		spriteMap.put(SlotType.SLOT_WIKI, new StaticSprite(AssetID.WIKI_IMG));
		spriteMap.put(SlotType.SLOT_YELP, new StaticSprite(AssetID.YELP_IMG));
		spriteMap.put(SlotType.SLOT_WORDPRESS, new StaticSprite(AssetID.WORDPRESS_IMG));
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		spriteMap.get(slotType).draw(gc, posX, posY);
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;		
	}
	
	public Sprite getSprite() {
		return spriteMap.get(slotType);
	}
	
}
