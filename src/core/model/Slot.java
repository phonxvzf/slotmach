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

public class Slot extends Entity {
	
	public static final List<SlotType> slotTypes = new ArrayList<SlotType>(
			Arrays.asList(
					SlotType.SLOT_K,
					SlotType.SLOT_O
			)
			);
	
	private SlotType slotType;
	private Map<SlotType, Sprite> spriteMap = new HashMap<SlotType, Sprite>();
	public Slot(GraphicsContext gc, SlotType type, double x, double y) {
		super(x, y);
		this.setSlotType(type);
		spriteMap.put(SlotType.SLOT_K, new StaticSprite(gc, AssetID.K_IMG));
		spriteMap.put(SlotType.SLOT_O, new StaticSprite(gc, AssetID.O_IMG));
	}

	public void draw() {
		spriteMap.get(slotType).draw(posX, posY);
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
