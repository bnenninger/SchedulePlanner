package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class UserInterface extends Stage {

	//NOTE: TAKE CARE IF ADDING CONSTRUCTOR
	//Stage seems to not be able to be initialized within a constructor of a controller
	//DOING SO CAUSES CRASHES
	
	
	protected void show(String title, EventHandler<WindowEvent> closeFunction, Modality modality) {
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
		super.setScene(scene);
		// blocks inputs to other windows until this window is closed
		super.initModality(modality);
		super.setTitle(title);
		// sets window to save if closed or saved
		super.setOnCloseRequest(closeFunction);
		super.showAndWait();
	}

	protected abstract String getFilePath();
}
