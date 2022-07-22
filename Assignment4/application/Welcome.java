package application;

import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public abstract class Welcome extends BaseScene {
	
	private ComboBox<Films> comboBox;
	
	private Button okButton = new Button();
	private Button logoutButton = new Button();	
	
	public void createComboBox() {
		for(Films film:ReadData.filmList) {
			getComboBox().getItems().add(film);
		}
		getComboBox().getSelectionModel().selectFirst();
	}
	
	public Button getOkButton() {
		return okButton;
	}
	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}
	public Button getLogoutButton() {
		return logoutButton;
	}
	public void setLogoutButton(Button logoutButton) {
		this.logoutButton = logoutButton;
	}
	public ComboBox<Films> getComboBox() {
		return comboBox;
	}
	public void setComboBox(ComboBox<Films> comboBox) {
		this.comboBox = comboBox;
	}
	

}
