package application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.converter.ByteStringConverter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		ReadData.readTheData("src\\assets\\data\\backup.dat");		
		ReadData.readTheProperties("src\\assets\\data\\properties.dat");
		
		primaryStage = BaseScene.mainStage;
		Image icon=new Image("file:src\\assets\\icons\\logo.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle(ReadData.properties.get("title"));
		primaryStage.centerOnScreen();
		
		Login login = new Login();
		login.setMainStageScene(login.getMainScene());;
		
		
		primaryStage.show();
	}
}
