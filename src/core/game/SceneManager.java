package core.game;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public final class SceneManager {

	private static Stage primaryStage;

	private static GameInstance gameInstance = new GameInstance();

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.setScene(new Scene(new Pane(gameInstance.getLoadingCanvas())));
		primaryStage.show();
	}

	public static void gotoGameScene() {
		gameInstance.startGame();
		primaryStage.setScene(new Scene(new Pane(new HBox(gameInstance.getStatusCanvas(), gameInstance.getMainGameCanvas()))));
		gameInstance.getMainGameCanvas().requestFocus();
	}

	public static void gotoNameInput() {
		gameInstance.getNameCanvas().startAnimation();
		primaryStage.setScene(new Scene(new Pane(new Pane(gameInstance.getNameCanvas()))));
		gameInstance.getNameCanvas().requestFocus();
	}
	
	public static void gotoGameOver() {
		gameInstance.getStatusCanvas().stopAnimation();
		gameInstance.getMainGameCanvas().stopAnimation();
		gameInstance.getGameOverCanvas().startAnimation();
		primaryStage.setScene(new Scene(new Pane(gameInstance.getGameOverCanvas())));
		gameInstance.getGameOverCanvas().requestFocus();
	}

}
