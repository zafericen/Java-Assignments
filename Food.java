
public class Food{
	
	//variables for food class
	private int ID = 0;
	private String name_of_food = new String();
	private  int cal_count = 0;
	
	// constructor for variables
	public Food(int ID,String name_of_food,int cal_count) {
		this.setID(ID);
		this.setName_of_food(name_of_food);
		this.setCal_count(cal_count);
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName_of_food() {
		return name_of_food;
	}
	public void setName_of_food(String name_of_food) {
		this.name_of_food = name_of_food;
	}
	public int getCal_count() {
		return cal_count;
	}
	public void setCal_count(int cal_count) {
		this.cal_count = cal_count;
	}
}

