package core.game;

import javafx.stage.Stage;
import core.settings.Settings;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public final class SceneManager {

	private static Stage primaryStage;

	private static GameInstance gameInstance = new GameInstance();
	private static Pane namePane = new Pane(gameInstance.getNameCanvas());
	private static Pane gamePane = new Pane(new HBox(gameInstance.getStatusCanvas(), gameInstance.getMainGameCanvas()));
	private static Scene gameScene;
	private static Scene nameScene;

	public static void initialize(Stage stage) {

		nameScene = new Scene(namePane);
		gameScene = new Scene(gamePane);
		gamePane.setMinSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		gamePane.setMaxSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		namePane.setMinSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		namePane.setMaxSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoGameScene() {
		gameInstance.startGame();
		primaryStage.setScene(gameScene);
		gameInstance.getNameCanvas().stopAnimation();
		gameInstance.getMainGameCanvas().requestFocus();
	}

	public static void gotoNameInput() {
		gameInstance.getNameCanvas().startAnimation();
		primaryStage.setScene(nameScene);
		if (gameInstance.isRunning())
			gameInstance.stopGame();
		gameInstance.getNameCanvas().requestFocus();
	}

}
