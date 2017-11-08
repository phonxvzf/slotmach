package core.model;

import core.asset.gfx.Sprite;
import core.settings.Settings;

public abstract class BasicEntity extends Entity implements IDrawable {
	protected Sprite sprite;
	
	public BasicEntity(Sprite sprite, double x, double y) {
		super((Settings.WINDOW_WIDTH-sprite.getWidth())/2, (Settings.WINDOW_HEIGHT-sprite.getHeight())/2);
		this.sprite = sprite;
	}
	
	@Override
	public void draw() {
		sprite.draw(posX, posY);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
