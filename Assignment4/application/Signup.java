package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Signup extends BaseScene{
	
	private Button login = new Button();
	private Button signup = new Button();
	private TextField userName = new TextField();
	private Text userNameText = new Text("Username:");
	private PasswordField passwordFirst = new PasswordField();
	private Text passwordTextFirst = new Text("Password:");
	private PasswordField passwordSecond = new PasswordField();
	private Text passwordTextSecond = new Text("Password:");
	
	public Signup() {
		setName("signup");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),360,260));
		setHeadlineText(new Text("    Welcome to the HUCS Cinema Reservation System!\n" +
	            "          Fill the form below to create new account.\n" +
	            "You can go to Log In page by clicking to LOG IN button."));
		
		initialize(userName, getRoot(), 130, 170, 70);
		
		initialize(userNameText, getRoot(), 95, 87);
		
		initialize(passwordFirst, getRoot(), 130, 170, 110);
		
		initialize(passwordTextFirst, getRoot(), 95, 127);
		
		initialize(passwordSecond, getRoot(), 130, 170, 150);
		
		initialize(passwordTextSecond, getRoot(), 95, 167);
	
		initialize(login, getRoot(),90 , 190,"LOG IN");
		
		initialize(signup, getRoot(), 250, 190, "SIGN UP");
		
		initialize(getHeadlineText(), getRoot(), 35, 30);
		
		getErrorText().setVisible(false);
		initialize(getErrorText(), getRoot(), 10, 240);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == getLogin()) {
			Login loginWindow = new Login();
			setMainStageScene(loginWindow.getMainScene());
		}
		else if (event.getSource() == getSignup()) {
				Users tempUser = new Users(userName.getText(), ReadData.hashPassword(passwordFirst.getText()), false, false);
				if (userName.getText().equals("")) {
					getErrorText().setText("                            ERROR: Username cannot be empty!");
					getErrorText().setVisible(true);
		            playErrorSound();
				}
				else if(passwordFirst.getText().equals("")){
		            getErrorText().setText("                             ERROR: Password cannot be empty!");
		            getErrorText().setVisible(true);
		            playErrorSound();
		        }
				else  if (!passwordFirst.getText().equals(passwordSecond.getText())) {
		            getErrorText().setText("                              ERROR: Passwords do not match!");
		            getErrorText().setVisible(true);
		            playErrorSound();
				}
				else if (ReadData.userList.indexOf(tempUser) != -1) {
					getErrorText().setText("                            ERROR: This username already exist!");
					getErrorText().setVisible(true);
		            playErrorSound();
				}
				else {
					ReadData.userList.add(tempUser);
					getErrorText().setText("SUCCESS: You have successfully registered with your new credentials!");
					getErrorText().setVisible(true);
					passwordFirst.clear();
					passwordSecond.clear();
					userName.clear();
				}
			}
		
		}

	public Button getLogin() {
		return login;
	}
	public Button getSignup() {
		return signup;
	}
	
	

}
