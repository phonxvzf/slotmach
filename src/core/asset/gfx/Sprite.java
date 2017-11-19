package core.asset.gfx;

public abstract class Sprite implements IDrawableXY {

	protected double width, height;
	
	public Sprite(double w, double h) {
		width = w;
		height = h;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
