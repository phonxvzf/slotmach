package core.asset.gfx;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite implements IDrawableXY {

	protected GraphicsContext graphicsContext;
	protected double width, height;
	
	public Sprite(GraphicsContext gc, double w, double h) {
		this.graphicsContext = gc;
		width = w;
		height =h;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
