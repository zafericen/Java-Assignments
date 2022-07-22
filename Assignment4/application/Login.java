package application;


import java.io.File;
import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Login extends BaseScene{
	
	
	private Button login = new Button();
	private Button signup = new Button();
	private TextField userName = new TextField();
	private Text userNameText = new Text("Username:");
	private PasswordField password = new PasswordField();
	private Text passwordText = new Text("Password:");
	private int waitNumber = Integer.parseInt(ReadData.properties.get("maximum-error-without-getting-blocked"));
	private int blockTime = Integer.parseInt(ReadData.properties.get("block-time"));
	
	
	
	
	public Login() {
		setName("login");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),360,220));
		setHeadlineText(new Text("    Welcome to the HUCS Cinema Reservation System!\n"
			+ "   Please enter your credentials below and click LOGIN.\n"
			+ "You can create a new account by clicking SING UP button."));
		
		initialize(userName, getRoot(), 140, 150, 80);
		
		initialize(userNameText, getRoot(), 85, 97);
		
		initialize(password, getRoot(), 140, 150, 120);
		
		initialize(passwordText, getRoot(), 85, 137);
	
		initialize(login, getRoot(), 240, 160,"LOG IN");
		
		initialize(signup, getRoot(), 80, 160, "SIGN UP");
		
		initialize(getHeadlineText(), getRoot(), 25, 20);
		
		getErrorText().setVisible(false);
		initialize(getErrorText(), getRoot(), 10, 205);
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == getLogin()) {
			if (checkPassword() && waitNumber>0) {
				checkAdmin_ClubMember();
				AdminWelcome adminWelcome = new AdminWelcome();
				UserWelcome userWelcome = new UserWelcome();
				switch (ReadData.userStatus[1]) {
					case "Admin-Club Member": {
						setMainStageScene(adminWelcome.getMainScene());
						break;
					}
					case "Admin":{
						setMainStageScene(adminWelcome.getMainScene());
						break;
					}
					case "Club Member":{
						setMainStageScene(userWelcome.getMainScene());
						break;
					}
					case "":{
						setMainStageScene(userWelcome.getMainScene());
						break;
					}
					
				}
				
				
			}
			else {
				playErrorSound();
				if (--waitNumber==0) {	
					PauseTransition pause = new PauseTransition(Duration.seconds(blockTime));
	                pause.setOnFinished(e -> {waitNumber = 4;getErrorText().setVisible(false);});
	                pause.play();
	                getErrorText().setText("ERROR: Please wait until end of the "+ blockTime +" seconds to make a new operation!");
	                getErrorText().setVisible(true);
				}else if (waitNumber>0){
					getErrorText().setText("\t\t\t\tERROR: There is no such a credential!");
					getErrorText().setVisible(true);
				}
			}
		}
		else if (event.getSource() == getSignup()) {
			Signup signup = new Signup();
			password.clear();
			userName.clear();
			setMainStageScene(signup.getMainScene());
		}
	}
	public Button getLogin() {
		return login;
	}
	public void setLogin(Button login) {
		this.login = login;
	}
	public Button getSignup() {
		return signup;
	}
	public void setSignup(Button signup) {
		this.signup = signup;
	}
	public Scene getLoginScene() {
		return getMainScene();
	}
	public void setLoginScene(Scene loginScene) {
		this.setMainScene(loginScene);
	}
	
	
	public boolean checkPassword() {
		String hashedPassword = ReadData.hashPassword(password.getText());
		Users tempUser = new Users(userName.getText(), hashedPassword, false, false);
		try {
			Users sameUser = ReadData.userList.get(ReadData.userList.indexOf(tempUser));
			if (sameUser.getPassword().equals(tempUser.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public void checkAdmin_ClubMember() {
		String hashedPassword = ReadData.hashPassword(password.getText());
		Users tempUser = new Users(userName.getText(), hashedPassword, false, false);
		ReadData.userStatus[0] = tempUser.getName();
		tempUser = ReadData.userList.get(ReadData.userList.indexOf(tempUser));
		if (tempUser.getIsAdmin() && tempUser.getIsClubMember()) {
			ReadData.userStatus[1] = "Admin-Club Member";
		}else if (tempUser.getIsAdmin() && !tempUser.getIsClubMember()) {
			ReadData.userStatus[1] = "Admin";
		}else if (tempUser.getIsClubMember() && !tempUser.getIsAdmin()) {
			ReadData.userStatus[1] = "Club Member";
		}else {
			ReadData.userStatus[1] = "";
		}
	}
}
