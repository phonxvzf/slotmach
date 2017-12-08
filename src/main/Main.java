package main;

import core.game.SceneManager;
import core.settings.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		// Initialize stage
		primaryStage.setTitle(Settings.WINDOW_TITLE);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		// Present to screen
		SceneManager.initialize(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}