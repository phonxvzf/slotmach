package core.model;

import java.util.Arrays;

import core.asset.AssetID;
import core.asset.gfx.AnimatedSprite;
import core.asset.gfx.StaticSprite;
import javafx.scene.canvas.GraphicsContext;

public class LightBox extends BasicEntity {

	private StaticSprite lightOff = new StaticSprite(AssetID.LIGHTX_IMG);
	private AnimatedSprite light;
	private boolean isOff = true;

	public LightBox(double x, double y) {
		super(new AnimatedSprite(Arrays.asList(AssetID.LIGHT0_IMG, AssetID.LIGHT1_IMG, AssetID.LIGHT2_IMG)), x, y);
		light = (AnimatedSprite) sprite;
		light.setTTL(100);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (isOff)
			lightOff.draw(gc, posX, posY);
		else
			sprite.draw(gc, posX, posY);
	}

	public void turnOff() {
		isOff = true;
		light.resetFrame();
	}

	public void turnOn() {
		isOff = false;
	}

}
