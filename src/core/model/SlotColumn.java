package core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.settings.Settings;
import javafx.scene.canvas.GraphicsContext;

public class SlotColumn extends Entity implements Drawable {

	private double height;
	private List<Slot> slotList = new ArrayList<Slot>();
	private Random randomizer = new Random();
	private boolean isPulled = false;

	private boolean isSlowDown = false;

	private SlotType getRandomSlotType() {
		return Slot.SLOT_TYPES.get(randomizer.nextInt(Slot.SLOT_TYPES.size()));
	}

	public SlotColumn(double x, double y, double height) {
		super(x, y);
		this.height = height;
		randomizer.setSeed(System.currentTimeMillis());

		double slotHeight = AssetCache.getImage(AssetID.K_IMG).getHeight();
		for (double pY = posY - slotHeight; pY < posY + height; pY += slotHeight) {
			Slot newSlot = new Slot(getRandomSlotType(), posX, pY);
			newSlot.setVelocity(0, Settings.SLOT_DEFAULT_VELOCITY);
			slotList.add(newSlot);
		}
	}

	public List<SlotType> getSlotType() {
		List<SlotType> ret = new ArrayList<SlotType>();
		int posofZero = (int) (slotList.get(0).posY / Settings.SLOT_DEFAULT_WIDTH);
		// There isn't the hidden slot above. So, position 0 in this array is the first
		// row of the column.
		int size = slotList.size();
		for (int i = 0; i < size - 1; ++i) {
			ret.add(slotList.get(Math.abs(size - posofZero + i) % size).getSlotType());
		}
		return ret;
	}

	public void update(long dt) {
		if (isSlowDown) {
			setSlotAccelY(Settings.SLOT_SLOWDOWN_ACCEL);
		} else {
			setSlotAccelY(0);
			setSlotVelocityY(Settings.SLOT_DEFAULT_VELOCITY);
		}
		if (getSlotVelocityY() < Settings.SLOT_MIN_VELOCITY) {
			setSlotVelocityY(Settings.SLOT_MIN_VELOCITY);
		}
		moveSlots(dt);
	}

	public void stop() {
		isPulled = true;
		setSlotFreeze(true);
		for (Slot slot : slotList) {
			double y = slot.posY;
			if (Math.ceil(slot.posY) > Settings.GAME_CANVAS_HEIGHT)
				slot.posY = 0;
			else if (Math.ceil(y) <= 0) {
				slot.posY = 0;
			} else {
				slot.posY = this.posY
						+ (Math.ceil((y - this.posY) / Settings.SLOT_DEFAULT_WIDTH) * Settings.SLOT_DEFAULT_WIDTH);
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (Slot slot : slotList) {
			slot.draw(gc);
		}
	}

	public void moveSlots(long dt) {
		double yOffset;
		for (Slot slot : slotList) {
			yOffset = slot.getPosY() - (posY + height);
			if (yOffset >= 0) {
				slot.setSlotType(getRandomSlotType());
				slot.setPos(posX, posY + yOffset - Settings.SLOT_DEFAULT_WIDTH);
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

	public void slowDown() {
		isSlowDown = true;
	}

	public void returnSpeed() {
		isSlowDown = false;
		isPulled = false;
		setSlotVelocityY(Settings.SLOT_DEFAULT_VELOCITY);
	}

	public boolean isPulled() {
		return isPulled;
	}
}
