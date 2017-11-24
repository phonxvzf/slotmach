package core.game;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public final class SceneManager {
	
	private static Stage primaryStage;
	
	private static GameInstance gameInstance;
	private static Scene gameScene;
	
	public static void initialize(Stage stage) {
		gameInstance = new GameInstance();
		gameScene = new Scene(new HBox(gameInstance.getStatusCanvas(), gameInstance.getMainGameCanvas()));
		primaryStage = stage;
		primaryStage.show();
	}
	
	public static void gotoGameScene() {
		gameInstance.startGame();
		primaryStage.setScene(gameScene);
		gameInstance.getMainGameCanvas().requestFocus();
	}
	
}
