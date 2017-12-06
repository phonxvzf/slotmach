package core.game;

import core.asset.AssetID;
import core.asset.sfx.MusicPlayer;
import core.model.Pricing;
import core.model.SlotType;
import core.settings.Settings;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class GameLogic {

	private Thread logicThread;
	private GameModel gameModel;
	private boolean isRunning;
	private long lastMatchAnimationTime;

	private MusicPlayer freezeSFX, blipSFX, yaySFX;

	public GameLogic(GameModel model) {
		gameModel = model;
		isRunning = false;
		freezeSFX = new MusicPlayer(AssetID.FREEZE_SFX, 1);
		blipSFX = new MusicPlayer(AssetID.BLIP_SFX, 1);
		yaySFX = new MusicPlayer(AssetID.YAY_SFX, 1);
		lastMatchAnimationTime = System.nanoTime();
	}

	public void startGame() {
		isRunning = true;
		logicThread = new Thread(this::logicLoop, "Game Logic Thread");
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
				updateGame(dt);
				lastLoopStartTime += dt;
			} else {
				// Delay to maintain FPS
				try {
					Thread.sleep((Settings.UPDATE_LOOP_TIME - dt) / 1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void updateGame(long dt) {
		// *** Beware of deadlock ***
		// Check for events

		// Check for key holds
		if (InputHandler.isKeyDown(KeyCode.F) && gameModel.gameState.getMana() > 0) {
			if (!gameModel.slotMachine.isSlowDown() && !gameModel.slotMachine.isAllStop()) {
				gameModel.gameState.giveMana(-Settings.SKILL_FREEZE_MPRATE_USE * dt / 1e9);
				gameModel.slotMachine.slowDown();
				if (!freezeSFX.isPlaying())
					freezeSFX.play();
			} else {
				if (gameModel.gameState.getMana() <= Settings.PLAYER_MAX_MANA) {
					gameModel.gameState.giveMana(Settings.SKILL_FREEZE_MPRATE_RECOVER * dt / 1e9);
				}
				gameModel.slotMachine.returnSpeed();
				freezeSFX.stop();
			}
		} else {
			if (gameModel.gameState.getMana() <= Settings.PLAYER_MAX_MANA) {
				gameModel.gameState.giveMana(Settings.SKILL_FREEZE_MPRATE_RECOVER * dt / 1e9);
			}
			freezeSFX.stop();
			gameModel.slotMachine.returnSpeed();
		}

		// Check for key presses
		KeyCode triggeredKey;
		while ((triggeredKey = InputHandler.pollTriggeredKey()) != null) {
			if (triggeredKey == KeyCode.SPACE) {
				if (gameModel.gameState.isCanPull()) {
					if (!gameModel.slotMachine.pull()) {
						if (gameModel.gameState.getMoney() >= Settings.PLAYER_PAID_PULL) {
							gameModel.gameState.setMoney(gameModel.gameState.getMoney() - Settings.PLAYER_PAID_PULL);
							gameModel.slotMachine.reset();
						}
					} else if (gameModel.slotMachine.isAllStop()) {
						// The player has pulled every column
						int prize = determinePrize(gameModel.slotMachine.getSlotCells());
						if (prize > 0) {
							// The player wins some slots
							yaySFX.play();
							gameModel.gameState.giveMoney(prize);
							gameModel.gameState.setCanPull(false);
							lastMatchAnimationTime = System.nanoTime();
						}
					}
					blipSFX.play();
				}

			} else if (triggeredKey == KeyCode.ESCAPE) {
				Platform.exit();
				System.exit(0);

			} else if (triggeredKey == KeyCode.RIGHT && gameModel.slotMachine.isAllStop()
					&& gameModel.slotMachine.getAddlerColumns() <= Settings.SLOT_DEFAULT_COLUMNS
							- Settings.SLOT_DEFAULT_BEGIN_COLUMNS - Settings.SLOT_DEFAULT_ADDLER
					&& gameModel.gameState.getMoney() >= Settings.PLAYER_PAID_EXCOL) {
				if (gameModel.gameState.isCanPull()) {
					gameModel.gameState.setMoney(gameModel.gameState.getMoney() - Settings.PLAYER_PAID_EXCOL);
					gameModel.slotMachine.reset();
					gameModel.slotMachine
							.setAddlerColumns(gameModel.slotMachine.getAddlerColumns() + Settings.SLOT_DEFAULT_ADDLER);
				}

			} else if (triggeredKey == KeyCode.LEFT && gameModel.slotMachine.isAllStop()
					&& gameModel.slotMachine.getAddlerColumns() >= Settings.SLOT_DEFAULT_ADDLER) {
				if (gameModel.gameState.isCanPull()) {
					gameModel.slotMachine.reset();
					gameModel.slotMachine
							.setAddlerColumns(gameModel.slotMachine.getAddlerColumns() - Settings.SLOT_DEFAULT_ADDLER);
				}

			} else if (triggeredKey == KeyCode.UP && gameModel.slotMachine.isAllStop()
					&& gameModel.slotMachine.getAddlerRow() <= Settings.SLOT_DEFAULT_ROWS
							- Settings.SLOT_DEFAULT_BEGIN_ROWS - Settings.SLOT_DEFAULT_ADDLER
					&& gameModel.gameState.getMoney() >= Settings.PLAYER_PAID_EXROW) {
				if (gameModel.gameState.isCanPull()) {
					gameModel.gameState.setMoney(gameModel.gameState.getMoney() - Settings.PLAYER_PAID_EXROW);
					gameModel.slotMachine.reset();
					gameModel.slotMachine
							.setAddlerRow(gameModel.slotMachine.getAddlerRow() + Settings.SLOT_DEFAULT_ADDLER);
				}

			} else if (triggeredKey == KeyCode.DOWN && gameModel.slotMachine.isAllStop()
					&& gameModel.slotMachine.getAddlerRow() >= Settings.SLOT_DEFAULT_ADDLER) {
				if (gameModel.gameState.isCanPull()) {
					gameModel.slotMachine.reset();
					gameModel.slotMachine
							.setAddlerRow(gameModel.slotMachine.getAddlerRow() - Settings.SLOT_DEFAULT_ADDLER);
				}

			} else if (triggeredKey == KeyCode.K && !gameModel.slotMachine.isBuyCol()
					&& gameModel.gameState.getMoney() >= Settings.PLAYER_PAID_BUYCOL
					&& !gameModel.slotMachine.isAllStop()) {
				if (gameModel.gameState.isCanPull()) {
					gameModel.gameState.setMoney(gameModel.gameState.getMoney() - Settings.PLAYER_PAID_BUYCOL);
					gameModel.slotMachine.setBuyCol(true);
					gameModel.slotMachine.setBuyColx(SlotType.SLOT_K);
				}
			}
		}

		// Handle animation time
		if (System.nanoTime() - lastMatchAnimationTime >= Settings.ANIMATION_MATCH_TIME
				&& !gameModel.gameState.isCanPull()) {
			gameModel.gameState.clearMatch();
			gameModel.gameState.setCanPull(true);
		}
	}

	private int determinePrize(SlotType[][] slotCells) {
		int prize = 0;
		int startRow = ((int) (Settings.SLOT_DEFAULT_COLUMN_HEIGHT / Settings.SLOT_DEFAULT_WIDTH)
				- (Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow())) / 2;
		int startCol = (Settings.SLOT_DEFAULT_COLUMNS / 2 - 1) - gameModel.slotMachine.getAddlerColumns() / 2;
		for (int i = 0; i < Settings.SLOT_DEFAULT_BEGIN_ROWS + gameModel.slotMachine.getAddlerRow(); ++i) {
			String slotCode = "";
			for (int j = 0; j < Settings.SLOT_DEFAULT_BEGIN_COLUMNS + gameModel.slotMachine.getAddlerColumns(); ++j) {
				slotCode += slotCells[startRow + i][startCol + j].getCode();
			}
			int prz = Pricing.getPrice(slotCode);
			if (prz > 0)
				gameModel.gameState.matchRow(startRow + i);
			prize += prz;
		}
		return prize;
	}
}
