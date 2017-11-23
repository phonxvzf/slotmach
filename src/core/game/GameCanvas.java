package core.game;

import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.settings.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {

	private GameModel gameModel;
	private boolean isContinue;
	private Thread animationThread;
	
	private StaticSprite frozenSprite = new StaticSprite(AssetID.FROZEN_IMG);
	
	public GameCanvas(GameModel model) {
		super(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		gameModel = model;
		isContinue = false;
		bindKeys();
	}
	
	public void startAnimation() {
		isContinue = true;
		animationThread = new Thread(this::animationLoop, "Animation Thread");
		animationThread.start();
	}
	
	public void stopAnimation() {
		isContinue = false;
	}
	
	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isContinue) {
			long dt = System.nanoTime() - lastLoopStartTime;
			if (dt >= Settings.LOOP_TIME) {
				updateAnimation(dt);
				lastLoopStartTime += dt;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateAnimation(long dt) {
		
		GraphicsContext gc = this.getGraphicsContext2D();
		
		// Clear everything
		gc.setGlobalAlpha(1.0f);
		gc.clearRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Perform calculations and image processing
		
		// Animate objects (e.g. obj.move(dt))
		gameModel.slotMachine.update(dt);
		
		// Draw entities
		gameModel.slotMachine.draw(gc);
		
		gc.setGlobalAlpha(gameModel.slotMachine.getColumns() == gameModel.slotMachine.getPullCount() 
				? 0.0f 
				: (Settings.SLOT_DEFAULT_VELOCITY 
				- gameModel.slotMachine.getSlotColumn(gameModel.slotMachine.getPullCount()).getSlotVelocityY())
				/ (Settings.SLOT_DEFAULT_VELOCITY - Settings.SLOT_MIN_VELOCITY)
				);
		frozenSprite.draw(gc, 0, 0);
	}
	
	private void bindKeys() {
		this.setOnKeyPressed(e -> {
			InputHandler.pressKey(e.getCode());
		});
		this.setOnKeyReleased(e -> {
			InputHandler.releaseKey(e.getCode());
		});
	}
	
}
