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
			} else {
				try {
					Thread.sleep((Settings.UPDATE_LOOP_TIME - dt) / 1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void updateGame(long dt) {
		// Be aware of deadlock
		// Check for events
		if (InputHandler.isKeyDown(KeyCode.S) && gameModel.gameState.getMana() > 0) {
			gameModel.slotMachine.slowDown();
			if (!gameModel.slotMachine.isSlowDown())
				gameModel.gameState.giveMana(-Settings.SKILL_FREEZE_MPRATE * dt / 1e9);
			else if (gameModel.gameState.getMana() <= Settings.PLAYER_MAX_MANA)
				gameModel.gameState.giveMana(Settings.SKILL_FREEZE_MPRATE * dt / 1e9);
		} else {
			if (gameModel.gameState.getMana() <= Settings.PLAYER_MAX_MANA) {
				gameModel.gameState.giveMana(Settings.SKILL_FREEZE_MPRATE * dt / 1e9);
			}
			gameModel.slotMachine.returnSpeed();
		}

		KeyCode triggeredKey;
		while ((triggeredKey = InputHandler.pollTriggeredKey()) != null) {
			if (triggeredKey == KeyCode.SPACE) {
				if (!gameModel.slotMachine.pull()) {
					gameModel.slotMachine.reset();
				}
			} else if (triggeredKey == KeyCode.ESCAPE) {
				Platform.exit();
				System.exit(0);
			}
		}
	}
}
