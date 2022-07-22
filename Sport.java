
public class Sport {

	// variables of sport class
	private int ID = 0;
	private String name_of_sport = new String();
	private  int cal_burned = 0;
	
	// constructor for variables
	public Sport(int ID,String name_of_sport,int cal_burned) {
		this.setID(ID);
		this.setName_of_sport(name_of_sport);
		this.setCal_burned(cal_burned);
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName_of_sport() {
		return name_of_sport;
	}
	public void setName_of_sport(String name_of_sport) {
		this.name_of_sport = name_of_sport;
	}
	public int getCal_burned() {
		return cal_burned;
	}
	public void setCal_burned(int cal_burned) {
		this.cal_burned = cal_burned;
	}
	//to calculate the calorie burned for specific time
	public int getCal_burned_hourly(int minute) {
		return (int) (cal_burned*(minute/60f));
	}
}