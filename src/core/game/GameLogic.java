package core.game;

import core.settings.Settings;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class GameLogic {
	
	private Thread logicThread;
	private GameModel gameModel;
	private boolean isRunning;
	
	public GameLogic(GameModel model) {
		gameModel = model;
		isRunning = false;
	}
	
	public void startGame() {
		isRunning = true;
		logicThread = new Thread(this::logicLoop, "Logic Thread");
		logicThread.start();
	}
	
	public void stopGame() {
		isRunning = false;
	}
	
	private void logicLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isRunning) {
			long dt = System.nanoTime() - lastLoopStartTime;
			if (dt >= Settings.UPDATE_LOOP_TIME) {
				updateGame(dt); // FIXED FPS
				lastLoopStartTime += dt;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateGame(long dt) {
		
		// Check for events
		if (InputHandler.isKeyDown(KeyCode.S)) {
			gameModel.slotMachine.slowDown(Settings.SLOT_SLOWDOWN_ACCEL, Settings.SLOT_MIN_VELOCITY);
		}
		else {
			gameModel.slotMachine.returnSpeed();
		}
		
		KeyCode triggeredKey;
		while ((triggeredKey = InputHandler.pollTriggeredKey()) != null) {
			if (triggeredKey == KeyCode.SPACE) {
				if (!gameModel.slotMachine.pullLever()) {
					gameModel.slotMachine.resetPullCount();
				}
			}
			else if (triggeredKey == KeyCode.ESCAPE) {
				Platform.exit();
			}
		}
	}
}
