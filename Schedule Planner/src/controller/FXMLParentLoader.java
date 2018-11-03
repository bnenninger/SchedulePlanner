package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FXMLParentLoader {
	
	private Parent root;
	
	public FXMLParentLoader(String filePath, Object controller) {
		FXMLLoader loader = new FXMLLoader();
		//pass controller to FXMLLoader
		loader.setController(controller);
		load(filePath, loader);
	}
	
	public FXMLParentLoader(String filePath) {
		FXMLLoader loader = new FXMLLoader();
		load(filePath, loader);
	}
	
	private void load(String filePath, FXMLLoader loader) {
		loader.setLocation(getClass().getResource(filePath));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(filePath + " failed to load.");
		}
	}
	
	public Parent getRoot() {
		return root;
	}
}
