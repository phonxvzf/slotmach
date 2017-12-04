package main;

import core.game.SceneManager;
import core.settings.Settings;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		// Initialize stage
		primaryStage.setTitle(Settings.WINDOW_TITLE);
		
		// Present to screen
		SceneManager.initialize(primaryStage);
		SceneManager.gotoNameInput();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}