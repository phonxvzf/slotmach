package core.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameCanvas extends Canvas {

	private AnimationTimer animationTimer;
	private long startTime;
	
	protected GameModel gameModel;
	protected GraphicsContext gc;
	
	public GameCanvas(GameModel model, double width, double height) {
		super(width, height);
		gameModel = model;
		gc = this.getGraphicsContext2D();
		bindKeys();
	}
	
	public void startAnimation() {
		startTime = System.nanoTime();
		animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				update(now - startTime);
				startTime = now;
			}
		};
		animationTimer.start();
	}
	
	public void stopAnimation() {
		animationTimer.stop();
	}
	
	protected abstract void update(long dt);
	protected abstract void bindKeys();
	
}
