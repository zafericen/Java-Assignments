package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public abstract class HallWindow extends BaseScene {
	 
	private Button backButton=new Button();
    private int row;
    private int column;
    private Image emptySeat=new Image(new File("src\\assets\\icons\\empty_seat.png").toURI().toString());
    private Image reservedSeat=new Image(new File("src\\assets\\icons\\reserved_seat.png").toURI().toString());
    private Halls selectedHall;
    private HashMap<Button,Seats> buttonMap = new HashMap<Button,Seats>();
    private Text informationText = new Text();
    private double discount = (100.0-Integer.parseInt(ReadData.properties.get("discount-percentage")))/100.0;
    
    public HallWindow(Halls hall) {
    	setName("HallWindow");
		setRoot(new Group());
		selectedHall = hall;
		row = selectedHall.getRow();
		column = selectedHall.getColumn();
		setMainScene(new Scene(getRoot(),row*70 + 90,column*70 + 150));
		
		initialize(backButton, getRoot(), 40, column*80, "◀ Back");
		
		initializeButtons();
		
		getErrorText().setVisible(false);
		initialize(getErrorText(), getRoot(), row*35 -20, column*70 + 100);
		informationText.setVisible(false);
		initialize(informationText, getRoot(), row*35-60, column*70+130);
    }
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == backButton) {
			UserFilmWindow userFilmWindow = new UserFilmWindow(selectedHall.getFilm());
			setMainStageScene(userFilmWindow.getMainScene());
		}else if (buttonMap.containsKey(event.getSource())) {
			Button button = (Button) event.getSource();
			Seats seat = buttonMap.get(button);
			if (seat.getOwner() == null) {
				buySeat(button,ReadData.getUser(ReadData.userStatus[0]));
			}else if (seat.getOwner().equals(ReadData.getUser(ReadData.userStatus[0]))) {
				refundSeat(button,ReadData.getUser(ReadData.userStatus[0]));
			}
		}
	}
	public void initializeButtons() {
		for (Seats[] seatsY : selectedHall.getSeatArray()) {
			for (Seats seat : seatsY) {
				Button button = new Button();
				initialize(button, getRoot(), seat.getRow()*70 +60, (seat.getCollumn())*70 +50, "");
				buttonMap.put(button, seat);
				if (seat.getOwner() == null) {
					ImageView emptySeatView = new ImageView(emptySeat);
					setButtonView(button, emptySeatView);
				}else if(!seat.getOwner().equals(ReadData.getUser(ReadData.userStatus[0]))) {
					ImageView reservedSeatView = new ImageView(reservedSeat);
					setButtonView(button, reservedSeatView);
					button.setDisable(true);
				}
				else {
					ImageView reservedSeatView = new ImageView(reservedSeat);
					setButtonView(button, reservedSeatView);
				}
			}
		}
	}
	public void setButtonView(Button button,ImageView imageView) {
		imageView.setFitWidth(45);
		imageView.setFitHeight(50);
		button.setGraphic(imageView);
	}
	public void buySeat(Button button,Users user) {
		Seats seat = buttonMap.get(button);
		seat.setOwner(user);
		int money = calculateMoney(seat);
		String textString = String.format("Seat at %d-%d is bought for %s for %d TL successfully !", seat.getRow()+1,
				seat.getCollumn()+1,seat.getOwner().getName(),money);
		informationText.setText(textString);
		informationText.setVisible(true);
		ImageView reservedSeatView = new ImageView(reservedSeat);
		setButtonView(button, reservedSeatView);
	}
	public void refundSeat(Button button,Users user) {
		Seats seat = buttonMap.get(button);
		seat.setOwner(null);
		String textString = String.format("Seat at %d-%d refunded successfully !", seat.getRow()+1,
				seat.getCollumn()+1);
		informationText.setText(textString);
		informationText.setVisible(true);
		ImageView empySeatView = new ImageView(emptySeat);
		setButtonView(button, empySeatView);
	}
	public int calculateMoney(Seats seat) {
		int money = selectedHall.getPricePerSeat();
		if (seat.getOwner().getIsClubMember()) {
			System.out.println(discount);
			money = (int) (money*discount);
		}
		return money;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Image getEmptySeat() {
		return emptySeat;
	}

	public void setEmptySeat(Image emptySeat) {
		this.emptySeat = emptySeat;
	}

	public Image getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(Image reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	public Halls getSelectedHall() {
		return selectedHall;
	}

	public void setSelectedHall(Halls selectedHall) {
		this.selectedHall = selectedHall;
	}

	public HashMap<Button, Seats> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(HashMap<Button, Seats> buttonMap) {
		this.buttonMap = buttonMap;
	}

	public Text getInformationText() {
		return informationText;
	}

	public void setInformationText(Text informationText) {
		this.informationText = informationText;
	}
	
}

