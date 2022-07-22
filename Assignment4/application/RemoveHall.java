package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.print.JobSettings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class RemoveHall extends BaseScene {
	
	private ComboBox<Halls> hallComboBox=new ComboBox<>();
    private Button okButton=new Button();
    private Button backButton=new Button();
    private Films selectedFilm;
    
    public RemoveHall(Films film) {
    	setName("removeHall");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),500,150));
		selectedFilm = film;
		getHeadlineText().setText("Select the hall you desire to remove from "+selectedFilm+" and then click OK");
		
		settingComboBox();
		
		initialize(okButton, getRoot(), 280, 90, "OK");
		
		initialize(backButton, getRoot(), 190, 90,"◀ Back");
		
		initialize(getHeadlineText(), getRoot(), 40, 30);
		
    }
    
	@Override
	public void handle(ActionEvent event) {
		
        if(event.getSource()==okButton){
            Halls removeHall = hallComboBox.getValue();
        	hallComboBox.getItems().remove(removeHall);
        	selectedFilm.getHallList().remove(removeHall);
        	ReadData.hallList.remove(removeHall);
        }
        else if(event.getSource()==backButton){
            AdminFilmWindow adminFilmWindow = new AdminFilmWindow(selectedFilm);
            setMainStageScene(adminFilmWindow.getMainScene());
        }

    }
		
	
	public void settingComboBox() {
		hallComboBox.getItems().addAll(selectedFilm.getHallList());
		hallComboBox.getSelectionModel().selectFirst();
		initialize(hallComboBox, getRoot(), 140, 180, 50);
	}
	

}
