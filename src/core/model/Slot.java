package core.model;

import java.util.ArrayList;
import java.util.List;

import core.asset.gfx.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Slot extends Entity implements Drawable {
	
	public static final List<SlotType> SLOT_TYPES = new ArrayList<SlotType>();
	public static void initialize() {
		for (SlotType slotType : SlotType.values()) {
			SLOT_TYPES.add(slotType);
		}
	}
	
	private SlotType slotType;
	
	public Slot(SlotType type, double x, double y) {
		super(x, y);
		this.setSlotType(type);
	}

	@Override
	public void draw(GraphicsContext gc) {
		slotType.getSprite().draw(gc, posX, posY);
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;		
	}
	
	public Sprite getSprite() {
		return slotType.getSprite();
	}
	
}
