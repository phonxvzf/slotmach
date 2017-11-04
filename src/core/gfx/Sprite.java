package core.gfx;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite implements IDrawable {

	protected GraphicsContext graphicsContext;
	
	public Sprite(GraphicsContext gc) {
		this.graphicsContext = gc;
	}
}
