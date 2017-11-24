package core.model;

import com.sun.javafx.tk.Toolkit;

import core.asset.gfx.Sprite;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ManaBar extends BasicEntity {

	private double barWidth, barHeight, maxAmount, currentWidth;
	private String what;
	private int r, g, b;
	private static Font font = new Font("optima", 20);
	private double textWidth;

	public ManaBar(Sprite sprite, double x, double y, double bwidth, double bheight, double max, String text) {
		super(sprite, x, y);
		barWidth = bwidth;
		barHeight = bheight;
		maxAmount = max;
		currentWidth = barWidth;
		what = text;
		textWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(text, font);
	}
	
	public void setAmount(double amount) {
		currentWidth = amount / maxAmount * barWidth;
	}
	
	public void setColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.rgb(100, 100, 100));
		gc.fillRect(posX, posY, barWidth, barHeight);
		gc.setFill(Color.rgb(r, g, b));
		gc.fillRect(posX, posY, currentWidth, barHeight);

		gc.setFill(Color.BLACK);
		gc.setFont(font);
		gc.setTextBaseline(VPos.TOP);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText(what, (posX - textWidth) - textWidth / what.length(), posY);
		sprite.draw(gc, posX, posY);
	}

}
