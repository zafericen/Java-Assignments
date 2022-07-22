package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class BaseScene implements EventHandler<ActionEvent>{
	
	private String name;
	private Group root;
	private Scene mainScene;
	private Text errorText = new Text();
	private Media errorSound = new Media(new File("src\\assets\\effects\\error.mp3").toURI().toString());
    private MediaPlayer errorPlayer = new MediaPlayer(errorSound);
    private Text headlineText =new Text();
	
	public static Stage mainStage = new Stage();
	
	public void initialize(Button button,Group root,int x ,int y,String name) {
		button.setOnAction(this);
		button.setLayoutX(x);
		button.setLayoutY(y);
		button.setText(name);
		root.getChildren().add(button);
	}
	public void initialize(Control field,Group root,int width,int x,int y) {
		field.setPrefWidth(width);
		field.setLayoutX(x);
		field.setLayoutY(y);
		root.getChildren().add(field);
	}
	public void initialize(Text text,Group root,int x,int y) {
		text.setLayoutX(x);
		text.setLayoutY(y);
		root.getChildren().add(text);
	}
	
	public void setMainStageScene(Scene scene) {
		BaseScene.mainStage.setScene(scene);
		BaseScene.mainStage.centerOnScreen();
		
	}
	
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Users)) {
            return false;
        }

        BaseScene u = (BaseScene) o;

        return getName().equals(u.getName());
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Group getRoot() {
		return root;
	}
	public void setRoot(Group root) {
		this.root = root;
	}
	public Scene getMainScene() {
		return mainScene;
	}
	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
	public Text getErrorText() {
		return errorText;
	}
	public void setErrorText(Text errorText) {
		this.errorText = errorText;
	}
	public MediaPlayer getErrorPlayer() {
		return errorPlayer;
	}
	public void setErrorPlayer(MediaPlayer errorPlayer) {
		this.errorPlayer = errorPlayer;
	}
	public void playErrorSound() {
		getErrorPlayer().seek(Duration.ZERO);
		getErrorPlayer().play();
	}
	public Text getHeadlineText() {
		return headlineText;
	}
	public void setHeadlineText(Text headlineText) {
		this.headlineText = headlineText;
	}
	
}
