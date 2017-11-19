package core.game;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public final class SceneManager {
	
	private static Stage primaryStage;
	
	private static GameInstance gameInstance;
	private static Scene gameScene;
	
	public static void initialize(Stage stage) {
		gameInstance = new GameInstance();
		gameScene = new Scene(new Pane(gameInstance.getGameCanvas()));
		primaryStage = stage;
		primaryStage.show();
	}
	
	public static void gotoGameScene() {
		gameInstance.startGame();
		primaryStage.setScene(gameScene);
		gameInstance.getGameCanvas().requestFocus();
	}
	
	public static void gotoSceneOf(Canvas canvas) {
		primaryStage.setScene(new Scene(new Pane(canvas)));
		canvas.requestFocus();
	}
	
}
