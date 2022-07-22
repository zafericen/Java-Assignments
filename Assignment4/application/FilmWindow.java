package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;


public abstract class FilmWindow extends BaseScene {
	private Films selectedFilm;
	
	private Media filmMedia;
    private MediaPlayer filmMediaPlayer;
    private MediaView filmMediaView;
    
    private Button backButton=new Button();
	private Button playButton=new Button();
    private Button backwardButton=new Button();
    private Button forwardButton=new Button();
    private Button playAgainButton=new Button();
    private Button okButton=new Button();
   

	private ComboBox<Halls> hallComboBox=new ComboBox<Halls>();
    
	private Slider slider =new Slider(0.0,1,0.5);
    
    
    
	public FilmWindow(Films film) {
    	setName("filmWindow");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),800,500));
		selectedFilm = film;
		
		selectFilm(selectedFilm);
    	
		settingTrailer();
		
        settingSlider();
		
        settingComboBox(selectedFilm);
		
		initialize(backButton, getRoot(), 230,460, "◀ Back");
		
		initialize(playButton, getRoot(), 735,100, "▶");
		playButton.setPrefWidth(50);
		
		initialize(backwardButton,getRoot(),735,140,"<<");
        backwardButton.setPrefWidth(50);

        initialize(forwardButton,getRoot(),735,180,">>");
        forwardButton.setPrefWidth(50);

        initialize(playAgainButton,getRoot(),735,220,"|<<");
        playAgainButton.setPrefWidth(50);
        
        initialize(okButton, getRoot(), 605, 460, "OK");
        
        getHeadlineText().setText(selectedFilm.toString()+" ("+selectedFilm.getDuration()+" minutes)");
        initialize(getHeadlineText(), getRoot(), 270, 30);
        
	}
    
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==backButton){
            UserWelcome userWelcome = new UserWelcome();
            setMainStageScene(userWelcome.getMainScene());
            filmMediaPlayer.stop();
            playButton.setText("▶");
        }
		else if(event.getSource()==playButton){
			
			if(filmMediaPlayer.getStatus() == Status.READY){
				playButton.setText("||");
	            filmMediaPlayer.play();
				
			}
			else{
				playButton.setText("▶");
	            filmMediaPlayer.pause();
	            
	        }
		}
        else if(event.getSource()==backwardButton){
        	double jump=filmMediaPlayer.getCurrentTime().toSeconds();
            jump = (jump-5)>0 ? jump-5 : 0;
            Duration duration = new Duration(jump*1000);
            filmMediaPlayer.seek(duration);
        }
        else if(event.getSource()==forwardButton){
        	double jump=filmMediaPlayer.getCurrentTime().toSeconds();
            double maxTime=filmMediaPlayer.getStopTime().toSeconds();
            jump=jump+5>=maxTime ? maxTime : jump+5;
            Duration duration = new Duration(jump*1000);
            filmMediaPlayer.seek(duration);
        }
        else if(event.getSource()==playAgainButton){
        	Duration duration = new Duration(0);
        	filmMediaPlayer.seek(duration);
        }
        else if (event.getSource() == okButton) {
			Halls hall = hallComboBox.getValue();
			UserHallWindow userHallWindow = new UserHallWindow(hall);
			getFilmMediaPlayer().stop();
            getPlayButton().setText("▶");
			setMainStageScene(userHallWindow.getMainScene());
			
		}
		
	}
	
	public void selectFilm(Films film) {
		filmMedia = new Media(new File("src\\assets\\trailers\\"+film.getTrailerPath()).toURI().toString());
		filmMediaPlayer = new MediaPlayer(filmMedia);
		filmMediaView = new MediaView(filmMediaPlayer);
		filmMediaPlayer.setVolume(0.5);
		}
	
	public void settingComboBox(Films film) {
		for(Halls hall:film.getHallList()) {
			hallComboBox.getItems().add(hall);
		}
		if(!hallComboBox.getItems().isEmpty()){
            hallComboBox.getSelectionModel().selectFirst();
        } 
		initialize(hallComboBox, getRoot(), 140, 455, 460);
        
	}
	public void settingSlider() {
		slider.setLayoutX(750);
		slider.setLayoutY(260);
        slider.setOrientation(Orientation.VERTICAL);
        getRoot().getChildren().add(slider);
        slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                filmMediaPlayer.setVolume(slider.getValue());
            }
        }); 
	}
	
	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getBackwardButton() {
		return backwardButton;
	}

	public void setBackwardButton(Button rewindButton) {
		this.backwardButton = rewindButton;
	}

	public Button getForwardButton() {
		return forwardButton;
	}

	public void setForwardButton(Button forwardButton) {
		this.forwardButton = forwardButton;
	}

	public Button getPlayAgainButton() {
		return playAgainButton;
	}

	public void setPlayAgainButton(Button goStartButton) {
		this.playAgainButton = goStartButton;
	}
	
	public MediaPlayer getFilmMediaPlayer() {
		return filmMediaPlayer;
	}

	public void setFilmMediaPlayer(MediaPlayer filmMediaPlayer) {
		this.filmMediaPlayer = filmMediaPlayer;
	}

	public Slider getSlider() {
		return slider;
	}

	public void setSlider(Slider slider) {
		this.slider = slider;
	}

	public ComboBox<Halls> getHallComboBox() {
		return hallComboBox;
	}

	public void setHallComboBox(ComboBox<Halls> hallDropDown) {
		this.hallComboBox = hallDropDown;
	}
	public void settingTrailer() {
		filmMediaView.setLayoutX(20);
		filmMediaView.setLayoutY(50);
		filmMediaView.setFitWidth(700);
		filmMediaView.setFitHeight(500);
		getRoot().getChildren().add(filmMediaView);
	}
	public Button getOkButton() {
		return okButton;
	}

	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}
	public Button getPlayButton() {
		return playButton;
	}

	public void setPlayButton(Button playButton) {
		this.playButton = playButton;
	}

	public Films getSelectedFilm() {
		return selectedFilm;
	}

	public void setSelectedFilm(Films selectedFilm) {
		this.selectedFilm = selectedFilm;
	}
	
	

	

}
