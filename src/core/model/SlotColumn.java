package core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.settings.Settings;
import javafx.scene.canvas.GraphicsContext;

public class SlotColumn extends Entity implements IDrawable {

	private double height;
	private List<Slot> slotList = new ArrayList<Slot>();
	private Random randomizer = new Random();

	private SlotType getRandomSlotType() {
		return Slot.slotTypes.get(randomizer.nextInt(Slot.slotTypes.size()));
	}

	public SlotColumn(GraphicsContext gc, double x, double y, double height) {
		super(x, y);
		this.height = height;
		randomizer.setSeed(System.currentTimeMillis());

		double slotHeight = AssetCache.getImage(AssetID.K_IMG).getHeight();
		for (double pY = posY - slotHeight; pY < posY + height; pY += slotHeight) {
			Slot newSlot = new Slot(gc, getRandomSlotType(), posX, pY);
			newSlot.setVelocity(0, Settings.SLOT_DEFAULT_VELOCITY);
			slotList.add(newSlot);
		}
	}

	public List<SlotType> getSlotType() {
		List<SlotType> ret = new ArrayList<SlotType>();
		// TODO
		// @author KOK
		return ret;
	}
	
	public void stop() {
		// TODO
		// @author KOK
	}

	@Override
	public void draw() {
		for (Slot slot : slotList) {
			slot.draw();
		}
	}

	public void moveSlots(long dt) {
		double yOffset;
		for (Slot slot : slotList) {
			yOffset = slot.getPosY() - (posY + height);
			if (yOffset >= 0) {
				slot.setSlotType(getRandomSlotType());
				slot.setPos(posX, posY + yOffset - slot.getSprite().getHeight());
			}
			slot.move(dt);
		}
	}

	public void setSlotFreeze(boolean frz) {
		for (Slot slot : slotList) {
			slot.setFreeze(frz);
		}
	}

	public void setSlotVelocityY(double newVel) {
		for (Slot slot : slotList) {
			slot.setVelY(newVel);
		}
	}

	public void setSlotAccelY(double newAccel) {
		for (Slot slot : slotList) {
			slot.setAccelY(newAccel);
		}
	}

	public double getSlotVelocityY() {
		return slotList.get(0).getVelY();
	}
}
