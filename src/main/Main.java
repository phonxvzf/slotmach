package main;

import java.util.HashSet;
import java.util.Set;

import core.asset.AssetCache;
import core.asset.AssetID;
import core.asset.gfx.StaticSprite;
import core.model.*;
import core.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private Canvas mainCanvas;
	private Group rootGroup;
	private Scene mainScene;
	private GraphicsContext graphicsContext;
	private AnimationTimer animationTimer;
	private Set<KeyCode> keyDown = new HashSet<KeyCode>();
	
	private long startTime;
	
	// Declare entities here
	// ======================
	// START DECLARE ENTITIES
	// ======================
	SlotMachine slotMachine;
	// ======================
	// END DECLARE ENTITIES
	// ======================

	private void mainLoopCallback(long currentTime) {
		final long dt = currentTime - startTime;
		
		// Clear back buffer
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Check for events
		if (keyDown.contains(KeyCode.S)) {
			slotMachine.slowDown(Settings.SLOT_SLOWDOWN_ACCEL, Settings.SLOT_MIN_VELOCITY);
		}
		else {
			slotMachine.returnSpeed();
		}
		
		// Perform calculations and image processing
		slotMachine.move(dt);
		
		// Animate objects (e.g. obj.move(dt))
		slotMachine.update(dt);
		
		// Draw entities
		slotMachine.draw();
		
		// Perform post-processing
		startTime = currentTime; // <--- This line is extremely important! Do not remove!
	}
	
	private void initObjects() {
		StaticSprite slotMachineBg = new StaticSprite(graphicsContext, AssetID.TEST_IMG);
		final int columns = Settings.SLOT_DEFAULT_COLUMNS;
		slotMachine = new SlotMachine(
				graphicsContext,
				slotMachineBg,
				(Settings.WINDOW_WIDTH - AssetCache.getImage(AssetID.K_IMG).getWidth() * columns) / 2.0f,
				0,
				columns);
	}
	
	private void bindKeys() {
		mainScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				if (!slotMachine.pullLever()) {
					slotMachine.resetPullCount();
				}
			}
			keyDown.add(e.getCode());
		});
		mainScene.setOnKeyReleased(e -> {
			keyDown.remove(e.getCode());
		});
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		// Load graphics and sounds
		AssetCache.loadAssets();
		
		// Initialize stage
		primaryStage.setTitle(Settings.WINDOW_TITLE);
		
		// Initialize canvas
		mainCanvas = new Canvas(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Initialize graphics context
		graphicsContext = mainCanvas.getGraphicsContext2D();
		
		// Initialize root node
		rootGroup = new Group();
		rootGroup.getChildren().add(mainCanvas);
		
		// Initialize scene
		mainScene = new Scene(rootGroup, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		bindKeys();
		
		// Allocate objects
		initObjects();
		
		// Present to screen
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		// Set main loop
		startTime = System.nanoTime();
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainLoopCallback(now);
			}
		};
		animationTimer.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}