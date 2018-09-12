package controller;

import javafx.fxml.FXML;
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

	public SaveRequest showAndReturn() {
//		response = SaveRequest.CANCEL;
		super.show("Schedule Planner", e -> {
			e.consume();
			cancel();
		}, Modality.APPLICATION_MODAL);
		return response;
	}
	
	@FXML
	private void cancel() {
		response = SaveRequest.CANCEL;
		close();
	}
	
	@FXML
	private void save() {
		response = SaveRequest.SAVE;
		close();
	}
	
	@FXML
	private void dontSave() {
		response = SaveRequest.DONT_SAVE;
		close();
	}
}
