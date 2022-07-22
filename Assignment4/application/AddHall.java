package application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddHall extends BaseScene {
	
	private Films selectedFilm;
	private Button backButton = new Button();
    private Button OKButton = new Button();
    private Text rowText = new Text("Row:");
    private Text columnText = new Text("Column:");
    private Text nameText = new Text("Name:");
    private Text priceText = new Text("Price:");
    private ComboBox<Integer> rowComboBox = new ComboBox<Integer>();
    private ComboBox<Integer> columnComboBox = new ComboBox<Integer>();
    private TextField nameField = new TextField();
    private TextField moneyField = new TextField();
    
	public AddHall(Films film) {
		setName("addHall");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),450,280));
		selectedFilm = film;
		getHeadlineText().setText(selectedFilm.toString()+" ("+selectedFilm.getDuration()+" minutes)");
		
		initialize(OKButton, getRoot(), 295, 190,"OK");
		initialize(backButton, getRoot(), 115, 190,"◀ Back");
		
		initialize(rowText, getRoot(), 115, 65);
		initialize(columnText, getRoot(), 115, 100);
		initialize(nameText, getRoot(), 115, 135);
		initialize(priceText, getRoot(), 115, 170);
		initialize(getHeadlineText(), getRoot(), 135, 30);
	
		
		settingComboBox();
		initialize(rowComboBox, getRoot(), 60, 225, 50);
		initialize(columnComboBox, getRoot(), 60, 225, 80);
		
		initialize(nameField, getRoot(), 140, 185, 115);
		initialize(moneyField, getRoot(), 140, 185, 150);
		
		getErrorText().setVisible(false);
		initialize(getErrorText(), getRoot(), 125, 240);
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==backButton){
            AdminFilmWindow adminFilmWindow = new AdminFilmWindow(selectedFilm);
            setMainStageScene(adminFilmWindow.getMainScene());
        }
        else if(event.getSource()==OKButton){
        	String nameString = nameField.getText();
        	String moneyString = moneyField.getText();
        	
        	getErrorText().setVisible(false);
        	
        	if (nameString.equals("")) {
				getErrorText().setText("   ERROR: Hall name could not be empty!");
				getErrorText().setVisible(true);
				playErrorSound();
			}else if (isHallNameExists(nameString)) {
				getErrorText().setText("   \tERROR: Hall name exists!");
				getErrorText().setVisible(true);
				playErrorSound();
			}
        	else {
				try {
					int moneyInt = Integer.parseInt(moneyString);
					int row = rowComboBox.getValue();
					int column = columnComboBox.getValue();
					if (moneyInt<=0) {
						 getErrorText().setText("ERROR: Price has to be positive integer!");
			             getErrorText().setVisible(true);
			             playErrorSound();
			             return;
					}
	        		Halls hall = new Halls(nameString, moneyInt,row,column);
					hall.initializeSeats(row, column);
					selectedFilm.getHallList().add(hall);
					ReadData.hallList.add(hall);
					getErrorText().setText("        SUCCESS: Hall added successfully!");
	                getErrorText().setVisible(true);
	                moneyField.clear();
	                nameField.clear();
				} catch (Exception e) {
					 getErrorText().setText("ERROR: Price has to be positive integer!");
		             getErrorText().setVisible(true);
		             playErrorSound();
				}
			}
        }
    }
	public void settingComboBox() {
		for(int i=3;i<11;i++){
            rowComboBox.getItems().add(i);
            columnComboBox.getItems().add(i);
        }
        
		rowComboBox.getSelectionModel().selectFirst();
        columnComboBox.getSelectionModel().selectFirst();
        
	}
	public boolean isHallNameExists(String hallName) {
		Halls tempHall = new Halls(hallName, 0, 0, 0);
		if (ReadData.hallList.indexOf(tempHall) == -1) {
			return false;
		}
		else {
			return true;
		}
	}

}
