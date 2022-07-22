package application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class AdminWelcome extends Welcome {
	private Button addFilmButton = new Button();
	private Button removeFilmButton = new Button();
	private Button editUsersButton = new Button();
	
	public AdminWelcome() {
		setName("adminWelcome");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),400,180));
		setComboBox(new ComboBox<Films>());
		createComboBox();
		
		setHeadlineText( new Text("  Welcome "+ReadData.userStatus[0]+" ("+ ReadData.userStatus[1] +")!\n" +
            "You can either select film below or do edits."));
		
		initialize(getHeadlineText(), getRoot(),90,30);
		
		initialize(getComboBox(), getRoot(), 280, 40,60);
		
		initialize(getLogoutButton(),getRoot(),300,130,"LOG OUT");
		
		initialize(getOkButton(),getRoot(),330,60,"OK");
		
		initialize(addFilmButton, getRoot(), 90, 95,"Add Film");
		
		initialize(removeFilmButton, getRoot(), 160,95,"Remove Film");
		
		initialize(editUsersButton, getRoot(), 250,95,"Edit Users");
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == getOkButton()) {
			AdminFilmWindow adminFilmWindow = new AdminFilmWindow(getComboBox().getValue());
			setMainStageScene(adminFilmWindow.getMainScene());
			
		}
		else if (event.getSource() == getLogoutButton()) {
			Login login = new Login();
			setMainStageScene(login.getMainScene());
		}
		else if (event.getSource() == getAddFilmButton()) {
			AddFilm addFilm = new AddFilm();
			setMainStageScene(addFilm.getMainScene());
		}
		else if (event.getSource() == getRemoveFilmButton()) {
			RemoveFilm removeFilm = new RemoveFilm();
			setMainStageScene(removeFilm.getMainScene());
		}
		else if (event.getSource() == getEditUsersButton()) {
			EditUsers editUsers = new EditUsers();
			setMainStageScene(editUsers.getMainScene());
		}
		
	}

	public Button getAddFilmButton() {
		return addFilmButton;
	}

	public void setAddFilmButton(Button addFilmButton) {
		this.addFilmButton = addFilmButton;
	}

	public Button getRemoveFilmButton() {
		return removeFilmButton;
	}

	public void setRemoveFilmButton(Button removeFilmButton) {
		this.removeFilmButton = removeFilmButton;
	}

	public Button getEditUsersButton() {
		return editUsersButton;
	}

	public void setEditUsersButton(Button editUsersButton) {
		this.editUsersButton = editUsersButton;
	}

}
