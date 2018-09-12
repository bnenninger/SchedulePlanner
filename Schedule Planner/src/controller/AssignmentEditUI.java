package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import assignmentStorage.Assignment;
import assignmentStorage.AssignmentType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

public class AssignmentEditUI extends UserInterface implements Initializable {

	@FXML
	protected TextField nameBox;
	@FXML
	protected ChoiceBox<String> assignmentTypeBox;
	@FXML
	protected DatePicker dueDatePicker;
	
	@FXML
	protected Button saveButton;

	private Assignment assignment;
	
	private boolean nameModified;
	private boolean assignmentTypeModified;
	private boolean dueDateModified;	

	public AssignmentEditUI(Assignment assignment) {
		this.assignment = assignment;
		nameModified = false;
		assignmentTypeModified = false;
		dueDateModified = false;
	}

	public AssignmentEditUI() {
		this(null);
	}

	public Assignment showAndReturn() {
		super.show("Edit Assignment", e -> {
			e.consume();
			closeButtonAction();
		}, Modality.APPLICATION_MODAL);
		//show waits to return until window closed
		return assignment;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		dueDatePicker.setValue(getDate());
		nameBox.setText(getNameText());
		assignmentTypeBox.setItems(FXCollections.observableArrayList(AssignmentType.getTypes()));
		assignmentTypeBox.setValue(AssignmentType.ASSIGNMENT.toString());
		//adds listeners for changes in the name and due date
		//deactivates save button when either is empty
		nameBox.textProperty().addListener(e -> {
			setSaveButtonDisable();
			nameModified = true;});
		dueDatePicker.setOnAction(e -> {
			setSaveButtonDisable();
			dueDateModified = true;});
		//TODO make this action change the boolean value
		assignmentTypeBox.setOnAction(e -> {assignmentTypeModified = true;});
		assignmentTypeBox.setDisable(false);
		setSaveButtonDisable();
	}

	@FXML
	private void save() {
		System.out.println("saved");
		String name = nameBox.getText();
		if(assignment != null) {
			if(nameModified) {
				assignment.setName(name);
			}
			if(dueDateModified) {
				assignment.setDueDate(dueDatePicker.getValue());
			}
			if(assignmentTypeModified) {
				assignment.setType(AssignmentType.valueOf(assignmentTypeBox.getValue().toUpperCase()));
			}
		}
		else {
			assignment = new Assignment(name, dueDatePicker.getValue());
			if(assignmentTypeBox.getValue() != null) {
				assignment.setType(AssignmentType.valueOf(assignmentTypeBox.getValue().toUpperCase()));
			}
		}
		super.close();
	}

	@Override
	protected String getFilePath() {
		return "/userInterfaceFXML/EditAssignment.fxml";
	}

	private void closeButtonAction() {
		//if the assignment has not been modified or is not saveable, close without saving
		if(!assignmentModified() || (!saveable() && assignmentModified())) {
			close();
			return;
		}
		SaveAlert alert = new SaveAlert();
		SaveAlert.SaveRequest choice = alert.showAndReturn();
		switch(choice) {
		case SAVE:
			save();
			break;
		case DONT_SAVE:
			close();
			break;
		case CANCEL:
			break;
		}
	}

	private void setSaveButtonDisable() {
		if(saveable()) {
			saveButton.setDisable(false);
		}
		else {
			saveButton.setDisable(true);
		}

	}

	private boolean saveable() {
		String name = nameBox.getText();
		return dueDatePicker.getValue() != null && name != null && name.length() > 0;
	}

	private String getNameText() {
		if(assignment != null) {
			return assignment.getName() + "";
		}
		return "";
	}

	private LocalDate getDate() {
		if(assignment != null) {
			return assignment.getDueDate();
		}
		return LocalDate.now();
	}
	
	private boolean assignmentModified() {
		//TODO add the rest of the assignment fields
		return nameModified || dueDateModified || assignmentTypeModified;
	}
}
