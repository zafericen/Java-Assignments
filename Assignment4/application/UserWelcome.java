package application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class UserWelcome extends Welcome {
	
	
	
	public UserWelcome() {
		setName("userWelcome");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),390,150));
		setComboBox( new ComboBox<Films>());
		createComboBox();
		
		setHeadlineText( new Text("\t\tWelcome "+ReadData.userStatus[0]+" "+ ReadData.userStatus[1] +"!\n" +
	            "Select a film adn click OK to continue."));
			
		initialize(getHeadlineText(), getRoot(),80,20);
		
		getComboBox().getSelectionModel().selectFirst();
		
		initialize(getComboBox(), getRoot(), 280, 30,50);
		
		
		initialize(getLogoutButton(),getRoot(),290,90,"LOG OUT");
		
		initialize(getOkButton(),getRoot(),320,50,"OK");
		
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == getOkButton()) {
			UserFilmWindow userFilmWindow = new UserFilmWindow(getComboBox().getValue());
			setMainStageScene(userFilmWindow.getMainScene());
			
		}
		else if (event.getSource() == getLogoutButton()) {
			Login login = new Login();
			setMainStageScene(login.getMainScene());
		}
		
	}

}
