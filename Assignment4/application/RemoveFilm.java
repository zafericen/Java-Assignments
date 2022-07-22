package application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class RemoveFilm extends BaseScene {
	
	private ComboBox<Films> removeFilmComboBox=new ComboBox<>();
    private Button backButton=new Button();
    private Button okbutton=new Button();
    

	public RemoveFilm() {
		setName("removeflim");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),380,130));
		setHeadlineText(new Text("Select the film that you desire to remove and then click OK."));
		
		initializeComboBox();
		
		initialize(removeFilmComboBox, getRoot(), 140, 40, 50);
		
		initialize(backButton, getRoot(), 140, 85, "◀ Back");
		
		initialize(okbutton, getRoot(), 200, 85, "OK");
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==backButton){
            AdminWelcome adminWelcome = new AdminWelcome();
            setMainStageScene(adminWelcome.getMainScene());
        }
		else if(event.getSource()==okbutton) {
			Films film = removeFilmComboBox.getValue();
			ReadData.filmList.remove(film);
			ReadData.hallList.removeAll(film.getHallList());
			removeFilmComboBox.getItems().remove(film);
			removeFilmComboBox.getSelectionModel().selectFirst();
		}
	}
	public void initializeComboBox() {
		for(Films film : ReadData.filmList) {
			removeFilmComboBox.getItems().add(film);
		}
		removeFilmComboBox.getSelectionModel().selectFirst();
	}

}
