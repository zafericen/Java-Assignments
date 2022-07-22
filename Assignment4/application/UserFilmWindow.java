package application;

import javafx.event.ActionEvent;

public class UserFilmWindow extends FilmWindow {

	public UserFilmWindow(Films film) {
		super(film);
		setName("userfilmWindow");
	}
	@Override
	public void handle(ActionEvent event) {
		super.handle(event);
	}

}
