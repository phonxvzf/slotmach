package core.asset;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class InvalidFileException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidFileException() {
		super("File requested is unavailable or invalid.");
	}

	public InvalidFileException(String what) {
		super("File format is not valid: " + what);
	}

	public InvalidFileException(String what, Throwable throwable) {
		super("Invalid File: " + what, throwable);
	}

	public void showAlertAndExit() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.ERROR, getMessage(), ButtonType.OK);
				alert.showAndWait();
				Platform.exit();
				System.exit(1);
			}
		});
	}
}
