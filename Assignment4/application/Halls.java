package application;

public class Halls {
	private String name;
	private int pricePerSeat ;
	private int row;
	private int column;
	private Seats[][] seatArray;
	private Films film;
	public Halls(String name,int pricePerSeat,int row,int collumn) {
		this.name=name;
		this.pricePerSeat=pricePerSeat;
		this.row=row;
		this.column=collumn;
		seatArray = new Seats[collumn][row];
	}
	public void initializeSeats(int row,int column) {
		for(int i=0;i<column;i++) {
			for (int j = 0; j < row; j++) {
				seatArray[i][j]= new Seats(film, this, null, row, column, pricePerSeat);
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPricePerSeat() {
		return pricePerSeat;
	}
	public void setPricePerSeat(int pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
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
	public void setColumn(int collumn) {
		this.column = collumn;
	}
	public Seats[][] getSeatArray() {
		return seatArray;
	}
	public void setSeatArray(Seats[][] seatArray) {
		this.seatArray = seatArray;
	}
	public Films getFilm() {
		return film;
	}
	public void setFilm(Films film) {
		this.film = film;
	}
	public void addSeat(int row,int collumn,Seats seat) {
		seatArray[collumn][row] = seat;
	}
	@Override
	public boolean equals(Object o) {
		 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Halls)) {
            return false;
        }

        Halls u = (Halls) o;

        return getName().equals(u.getName());
    }
	@Override
	public String toString() {
		return getName();
	}
	
}
