package application;

import java.time.LocalDate;

import assignmentStorage.Assignment;
import controller.AssignmentEditUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public final String FILE_EXTENSION = ".scdl";
	public final String DEFAULT_FILENAME = "schedule";
	public final String DEFAULT_FILEPATH = "C:\\Users\\Brendan Nenninger\\Documents\\programming\\java\\Schedule Planner\\";
	//public final String DEFAULT_SAVE_LOCATION = DEFAULT_FILEPATH + DEFAULT_FILENAME + FILE_EXTENSION;
	
	@Override
	public void start(Stage primaryStage) {
		Assignment test = new Assignment("test assignment", LocalDate.of(2018, 11, 26));
		//Parent root = FXMLLoader.load(getClass().getResource("primaryLayout.fxml"));
		Button button = new Button("Click me");
		button.setOnAction(e -> {
			AssignmentEditUI edit = new AssignmentEditUI(test);
			System.out.println(edit.show());
		});
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout,400,400);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Schedule Planner");
		primaryStage.show();
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
