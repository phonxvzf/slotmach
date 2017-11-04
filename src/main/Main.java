package main;

import core.gfx.AssetCache;
import core.gfx.AssetID;
import core.gfx.StaticSprite;
import core.model.Background;
import core.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
	private Canvas mainCanvas;
	private Group rootGroup;
	private Scene mainScene;
	private GraphicsContext graphicsContext;
	
	private long startTime;
	
	private Background background;
	private void mainLoopCallback(long currentTime) {
		final long dt = currentTime - startTime;
		// Perform calculations
		
		// Clear back buffer
		graphicsContext.clearRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// Animate objects (e.g. obj.move(dt))
		background.move(dt);
		
		// Draw entities
		background.draw();
		
		// Perform post-calculations
		startTime = currentTime;
	}
	
	private void initObjects() {
		background = new Background(new StaticSprite(graphicsContext, AssetID.TEST_IMG), 0, 0);
		background.setVelocity(0.0f, 0.0f);
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
