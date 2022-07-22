import java.util.ArrayList;

public abstract class Squares {
	//variables
	private String name;
	private int location;
	private int rolledDice;
	//static variables for method usage and containing data
	public static Banker banker;
	public static ArrayList<Squares> arrOfSquares = new ArrayList<Squares>();

	public Squares(String name,int location) {
		setName(name);
		setLocation(location);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	//for dice related calculations
	public int getRolledDice() {
		return rolledDice;
	}

	public void setRolledDice(int rolledDice) {
		this.rolledDice = rolledDice;
	}
	//equals method for extracting same object from a list of object without distortion
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Properties)) {
            return false;
        }

        Squares s = (Squares) o;

        return getLocation()==s.getLocation();
    }
	
	
}
