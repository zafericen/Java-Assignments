
public abstract class Properties extends Squares{
	private int cost;
	//owner field for checking if the player lands on square is the same as owner
	private Users owner;
	public Properties(String name, int location,int cost) {
		super(name, location);
		setCost(cost);
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int price) {
		this.cost = price;
	}
	public Users getOwner() {
		return owner;
	}
	public void setOwner(Users users) {
		this.owner = users;
	}
	//abstract method for calculating rent 
	public abstract int getRent();
	
	
}

