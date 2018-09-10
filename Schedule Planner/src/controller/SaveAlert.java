package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Modality;

public class SaveAlert extends UserInterface {

	public enum SaveRequest {
		SAVE, DONT_SAVE, CANCEL;
	}
	
	@FXML
	protected Button saveButton;
	@FXML
	protected Button dontSaveButton;
	@FXML
	protected Button cancelButton;
	
	private SaveRequest response;
	
	protected String getFilePath() {
		return "/userInterfaceFXML/SaveAlert.fxml";
	}

	public SaveRequest display() {
//		response = SaveRequest.CANCEL;
		super.show("Schedule Planner", e -> {
			e.consume();
			cancel();
		}, Modality.APPLICATION_MODAL);
		return response;
	}
	
	@FXML
	protected void cancel() {
		response = SaveRequest.CANCEL;
		close();
	}
	
	@FXML
	public void save() {
		response = SaveRequest.SAVE;
		close();
	}
	
	@FXML
	protected void dontSave() {
		response = SaveRequest.DONT_SAVE;
		close();
	}
}
