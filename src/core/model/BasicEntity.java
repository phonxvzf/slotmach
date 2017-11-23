package core.model;

import core.asset.gfx.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class BasicEntity extends Entity implements Drawable {
	protected Sprite sprite;
	
	public BasicEntity(Sprite sprite, double x, double y) {
		super(x, y);
		this.sprite = sprite;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		sprite.draw(gc, posX, posY);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
