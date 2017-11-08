package main;

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
	
	private long startTime;
	
	// Declare entities here
	// START DECLARE ENTITIES
	SlotMachine slotMachine;
	
	// END DECLARE ENTITIES

	private void mainLoopCallback(long currentTime) {
		final long dt = currentTime - startTime;
		
		// Clear back buffer
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Perform calculations and image processing
		slotMachine.update(dt);
		
		// Animate objects (e.g. obj.move(dt))
		slotMachine.move(dt);
		
		// Draw entities
		slotMachine.draw();
		
		// Perform post-processing
		startTime = currentTime; // <--- This line is extremely important! Do not remove!
	}
	
	private void initObjects() {
		slotMachine = new SlotMachine(
				graphicsContext,
				new StaticSprite(graphicsContext, AssetID.TEST_IMG),
				Settings.WINDOW_WIDTH /2-75,
				//0,
				0,
				3);
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
		mainScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.SPACE) {
					if (!slotMachine.pullLever()) {
						slotMachine.resetPullCount();
					}
				}
			}
		);
		
		// Allocate objects
		initObjects();
		
		// Set main loop
		startTime = System.nanoTime();
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainLoopCallback(now);
			}
		}.start();
		
		// Present to screen
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
