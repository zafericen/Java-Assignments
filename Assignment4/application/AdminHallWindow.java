package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AdminHallWindow extends HallWindow {

	private ComboBox<Users> usersBox = new ComboBox<Users>();
	
	public AdminHallWindow(Halls hall) {
		super(hall);
		
		settingComboBox();
		initialize(usersBox, getRoot(), 140, getRow()*35 -20, getColumn()*70 + 50);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == getBackButton()) {
			AdminFilmWindow adminFilmWindow = new AdminFilmWindow(getSelectedHall().getFilm());
			setMainStageScene(adminFilmWindow.getMainScene());
		}else if (getButtonMap().containsKey(event.getSource())) {
			Button button = (Button) event.getSource();
			Seats seat = getButtonMap().get(button);
			if (seat.getOwner() == null) {
				buySeat(button,usersBox.getValue());
			}else if (seat.getOwner().equals(usersBox.getValue())) {
				refundSeat(button,usersBox.getValue());
			}
		}
		
	}
	
	public void setMauseEffect(Button button) {
		button.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
					@Override
			          public void handle(MouseEvent e) {
			            Seats seat = getButtonMap().get(button);
						String textString = seat.getOwner()==null?"Not bought yet":"Bought by "+
								seat.getOwner().getName()+" for "+calculateMoney(seat)+" TL!";
						getErrorText().setText(textString);
						getErrorText().setVisible(true);
			          }
			        });

		button.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			            getErrorText().setVisible(false);;
			          }
			        });
	}
	@Override
	public void initializeButtons() {
		super.initializeButtons();
		for(Button button:getButtonMap().keySet()) {
			setMauseEffect(button);
			button.setDisable(false);
		}
	}
	public void settingComboBox() {
		usersBox.getItems().addAll(ReadData.userList);
		usersBox.getSelectionModel().select(ReadData.getUser(ReadData.userStatus[0]));
	}
}
