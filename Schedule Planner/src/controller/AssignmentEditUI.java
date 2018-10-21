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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

public class AssignmentEditUI extends UserInterface implements Initializable {

	@FXML
	protected TextField nameBox;
	@FXML
	protected ChoiceBox<AssignmentType> assignmentTypeBox;
	@FXML
	protected DatePicker dueDatePicker;
	@FXML
	protected TextArea descriptionBox;
	
	@FXML
	protected Button saveButton;

	private Assignment assignment;
		

	public AssignmentEditUI(Assignment assignment) {
		this.assignment = assignment;
	}

	public AssignmentEditUI() {
		this(null);
	}

	public Assignment showAndReturn() {
		super.show("Edit Assignment", e ->
		{
			e.consume();
			closeButtonAction();
		},
		Modality.APPLICATION_MODAL);
		//show waits to return until window closed
		return assignment;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		assignmentTypeBox.setItems(FXCollections.observableArrayList(AssignmentType.values()));
		assignmentTypeBox.setValue(getAssignmentType());
		nameBox.setText(getNameText());
		dueDatePicker.setValue(getDate());
		//adds listeners for changes in the name and due date
		//deactivates save button when either is empty
		nameBox.textProperty().addListener(e -> setSaveButtonDisable());
		dueDatePicker.setOnAction(e -> setSaveButtonDisable());
		setSaveButtonDisable();//sets proper initial save button state
		descriptionBox.setText(getDescription());
	}

	@FXML
	private void save() {
		/*
		 * saves all modified fields
		 * avoids having boolean to store if a field has been modified
		 */
		System.out.println("saved");//TODO remove when class finished
		String name = nameBox.getText();
		AssignmentType type = assignmentTypeBox.getValue();
		if(assignment != null) {
			assignment.setName(name);
			assignment.setDueDate(dueDatePicker.getValue());
			assignment.setType(type);
		}
		else {
			assignment = new Assignment(name, dueDatePicker.getValue(), type);
		}
		assignment.setDescription(descriptionBox.getText());//passes null if null
		super.close();
	}
	
	public Assignment getAssignment() {
		return assignment;
	}

	@Override
	protected String getFilePath() {
		return "/userInterfaceFXML/EditAssignment.fxml";
	}

	private void closeButtonAction() {
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
	
	private AssignmentType getAssignmentType() {
		if(assignment != null) {
			return assignment.getType();
		}
		return AssignmentType.ASSIGNMENT;
	}
	
	private String getDescription() {
		if(assignment != null) {
			return assignment.getDescription();
		}
		return "";
	}
}
