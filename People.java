
public class People {
	
	//variables for people class
	private int ID = 0;
	private String name = new String();
	private String gender = new String();
	private int weight = 0;
	private int height = 0;
	private int date_of_birth = 0;
	private int cal_need = 0;
	private int cal_burned = 0;
	private int cal_taken = 0;
	
	//constructor for variables
	public  People(int ID,String name,String gender,int weight,int height,int date_of_birth) {
		this.setID(ID);
		this.setName(name);
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.date_of_birth = date_of_birth;
		Cal_Calculator();
	}
	// calorie calculator for gender of an object
	private void Cal_Calculator() {
		if (gender.equals("male")) {
			float cal_need_temp = 66 + (13.75f*weight) + (5*height) - (6.8f*(2022-date_of_birth));
			setCal_need(Math.round(cal_need_temp));
		}else if (gender.equals("female")) {
			float cal_need_temp = 665 + (9.6f * weight) + (1.7f * height) - (4.7f * (2022-date_of_birth));
			setCal_need(Math.round(cal_need_temp));
		}
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCal_need() {
		return cal_need;
	}
	public void setCal_need(int cal_need) {
		this.cal_need = cal_need;
	}
	public int getCal_burned() {
		return cal_burned;
	}
	public void setCal_burned(int cal_burned) {
		this.cal_burned = cal_burned;
	}
	public int getCal_taken() {
		return cal_taken;
	}
	public void setCal_taken(int cal_taken) {
		this.cal_taken = cal_taken;
	}
	// calorie taken from a food
	public void Cal_add(int cal_taken) {
		this.setCal_taken(this.getCal_taken() + cal_taken);
	}
	// calorie burned because of a sport
	public void Cal_loss(int cal_burned) {
		this.setCal_burned(this.getCal_burned() + cal_burned);
	}
	//net calorie need for a day
	public int getCal_total() {
		return (getCal_taken()-getCal_burned())-getCal_need();
	}
	//calorie string for printWarn function
	public String getCal_total_string() {
		int cal_total = getCal_total();
		if (cal_total>0) {
			return "+"+cal_total;
		}return String.valueOf(cal_total);
	}
	//print function returning information about an object
	public String PrintInfo() {
		return getName()+"\t"+(2022-date_of_birth)+"\t"+ getCal_need()+"kcal"+"\t"+getCal_taken()+"kcal"+"\t"+
	getCal_burned()+"kcal"+"\t"+ getCal_total_string()+"kcal" + "\n";
	}
}
