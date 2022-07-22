package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class AdminFilmWindow extends FilmWindow {
	private Button addHallButton=new Button();
    private Button removeHallButton=new Button();
    
	
	public AdminFilmWindow(Films film) {
		super(film);
		setName("adminfilmWindow");
		initialize(getAddHallButton(),getRoot(),290,460,"Add Hall");

        initialize(getRemoveHallButton(),getRoot(),360,460,"Remove Hall");
	}
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==getBackButton()){
            AdminWelcome adminWelcome = new AdminWelcome();
            setMainStageScene(adminWelcome.getMainScene());
            getFilmMediaPlayer().stop();
            getPlayButton().setText("▶");
        }
		else if(event.getSource()==addHallButton){
			AddHall addHall = new AddHall(getSelectedFilm());
			getFilmMediaPlayer().stop();
            getPlayButton().setText("▶");
			setMainStageScene(addHall.getMainScene());
			
		}
        else if(event.getSource()==removeHallButton){
            RemoveHall removeHall = new RemoveHall(getSelectedFilm());
            getFilmMediaPlayer().stop();
            getPlayButton().setText("▶");
            setMainStageScene(removeHall.getMainScene());
        }
        else if (event.getSource() == getOkButton()) {
			Halls hall = getHallComboBox().getValue();
			AdminHallWindow adminHallWindow = new AdminHallWindow(hall);
			getFilmMediaPlayer().stop();
            getPlayButton().setText("▶");
			setMainStageScene(adminHallWindow.getMainScene());
			
		}
        else {
        	super.handle(event);
		}
	}
	public Button getAddHallButton() {
		return addHallButton;
	}

	public void setAddHallButton(Button addHallButton) {
		this.addHallButton = addHallButton;
	}

	public Button getRemoveHallButton() {
		return removeHallButton;
	}

	public void setRemoveHallButton(Button removeHallButton) {
		this.removeHallButton = removeHallButton;
	}
}
