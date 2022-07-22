import java.util.ArrayList;

public abstract class Users {
	private String name;
	private int money = 0;
	private int location = 1;
	//users owned properties, every property is owned by banker at the start of the game
	private ArrayList<Properties> arrOfProperties = new ArrayList<Properties>();

	public Users(String name,int money) {
		setName(name);
		setMoney(money);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	//changing money
	public void setMoney(int money) {
		this.money += money;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	//teleporting any user to specific location
	public void teleportToLocation(int locationChange) {
		if (0>locationChange) {
			this.location = (getLocation() + locationChange);
		}else {
			this.location = (locationChange);
		}
	}
	//moving a user amount of dice
	public void addLocation(int location) {
		int tempLocation = getLocation();
		tempLocation += location;
		//taking mod40 of location for location not to leave 1-40 location range
		this.location = (((tempLocation-1)%40)+1);
	}
	public ArrayList<Properties> getArrOfProperties() {
		return arrOfProperties;
	}
	//adding property to user
	public void addPropertie(Properties properties) {
		getArrOfProperties().add(properties);
		properties.setOwner(this);
	}
	//equals method for getting same user
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Properties)) {
            return false;
        }

        Users u = (Users) o;

        return getName().equals(u.getName());
    }
}
