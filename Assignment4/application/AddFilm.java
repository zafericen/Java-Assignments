package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddFilm extends BaseScene {
	
	private TextField name = new TextField();
    private TextField trailerPath = new TextField();
    private TextField duration = new TextField();
    private Text nameText = new Text("Name:");
    private Text trailerpathText =new Text("Trailer (path):");
    private Text durationText = new Text("Duration (m):");
    private Text informationText = new Text("Please give name, relative path of the trailer and duration of the film.");
    private Button backButton = new Button();
    private Button okButton = new Button();
	
    public AddFilm() {
    	setName("addFilm");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),400,220));
		
		initialize(name, getRoot(), 140, 160, 40);
		
		initialize(trailerPath, getRoot(), 140, 160, 80);
		
		initialize(duration, getRoot(), 140, 160, 120);
		
		initialize(nameText, getRoot(), 80, 55);
		
		initialize(trailerpathText, getRoot(), 80, 95);
		
		initialize(durationText, getRoot(), 80, 135);
		
		initialize(informationText, getRoot(), 20, 25);
		
		initialize(backButton, getRoot(), 80, 155, "◀ Back");
		
		initialize(okButton, getRoot(), 265, 155, "OK");
		
		getErrorText().setVisible(false);
		initialize(getErrorText(), getRoot(), 70, 200);
    }
    
	@Override
	public void handle(ActionEvent event) {
		
		if(event.getSource()==backButton){
            AdminWelcome adminWelcome = new AdminWelcome();
            setMainStageScene(adminWelcome.getMainScene());
        }
        else if(event.getSource()==okButton){
            String nameString = name.getText();
            String trailerPathString = trailerPath.getText();
            String durationString = duration.getText();
            getErrorText().setVisible(false);
            if (nameString.equals("")) {
				getErrorText().setText("\tERROR: Film name could not be empty!");
				getErrorText().setVisible(true);
				playErrorSound();
			}
            else if (trailerPathString.equals("")) {
            	getErrorText().setText("\t\tERROR: Trailer path could not be empty!");
                getErrorText().setVisible(true);
                playErrorSound();
			}
            else if (!pathExists(trailerPathString)) {
            	 getErrorText().setText("\t\t\tERROR: There is no such trailer!");
                 getErrorText().setVisible(true);
                 playErrorSound();
            }
            else {
				try {
	            	int durationInt = Integer.parseInt(durationString);
	            	if (durationInt<=0) {
	            		getErrorText().setText("ERROR: Duration has to be positive integer!");
		                getErrorText().setVisible(true);
		                playErrorSound();
		                return;
					}
	            	Films film = new Films(nameString, trailerPathString, durationInt);
	            	ReadData.filmList.add(film);
	            	getErrorText().setText("\tSUCCESS: Film added successfully!");
	                getErrorText().setVisible(true);
	            	
				} catch (Exception e) {
					getErrorText().setText("ERROR: Duration has to be positive integer!");
	                getErrorText().setVisible(true);
	                playErrorSound();				
	                }
            }
        }
	}
	public boolean pathExists(String path) {
        boolean pathExist = new File("assets\\trailers\\" + path).exists();
        return pathExist;
	}
}
