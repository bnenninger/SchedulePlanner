package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class UserInterface {

//	protected void show() throws IOException {
//		Stage window = new Stage();
//		show(window);
//	}

	//NOTE: TAKE CARE IF ADD CONSTRUCTOR
	//Stage seems to not be able to be initialized within a constructor of a controller
	//DOING SO CAUSES CRASHES
	
	private Stage window;
	
	protected void show(String title, EventHandler<WindowEvent> closeFunction, Modality modality) {
		window = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(getFilePath()));
		//pass controller to FXMLLoader
		loader.setController(this);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(getFilePath() + " failed to load.");
		}
		Scene scene = new Scene(root);
		window.setScene(scene);
		// blocks inputs to other windows until this window is closed
		window.initModality(modality);
		window.setTitle(title);
		// sets window to save if closed or saved
		window.setOnCloseRequest(closeFunction);
		window.showAndWait();
	}

	protected abstract String getFilePath();
	
	public void close() {
		window.close();
	}
}
