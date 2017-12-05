package core.asset;

import javafx.scene.control.ButtonType;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidAssetException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAssetException() {
		super("The asset requested is unavailable or invalid.");
	}
	
	public InvalidAssetException(String what) {
		super("Invalid asset: " + what);
	}
	
	public InvalidAssetException(String what, Throwable throwable) {
		super("Invalid asset: " + what, throwable);
	}
	
	public Optional<ButtonType> showAlert() {
		Alert alert = new Alert(AlertType.ERROR, this.getMessage(), ButtonType.OK);
		return alert.showAndWait();
	}
	
	public void showAlertAndExit() {
		this.showAlert();
		Platform.exit();
		System.exit(1);
	}

}
