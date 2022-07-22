package application;

public class Seats {
	private Films film;
	private Halls hall;
	private Users owner;
	private int row;
	private int collumn;
	private int money;
	
	public Seats(Films film,Halls hall,Users owner,int row,int collumn,int money) {
		this.owner=owner;
		this.film=film;
		this.hall=hall;
		this.row=row;
		this.collumn=collumn;
		this.money=money;
	}
	
	
	public Users getOwner() {
		return owner;
	}
	public void setOwner(Users owner) {
		this.owner = owner;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCollumn() {
		return collumn;
	}
	public void setCollumn(int collumn) {
		this.collumn = collumn;
	}
	public int getMoney() {
		
		return money;
		
	}
	public void setMoney(int money) {
		this.money = money;
	}


	public Films getFilm() {
		return film;
	}


	public void setFilm(Films film) {
		this.film = film;
	}


	public Halls getHall() {
		return hall;
	}


	public void setHall(Halls hall) {
		this.hall = hall;
	}
	
}
