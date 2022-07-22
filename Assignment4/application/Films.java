package application;

import java.util.ArrayList;

public class Films {
	private String name;
	private String trailerPath;
	private int duration;
	private ArrayList<Halls> hallList = new ArrayList<Halls>() ;
	
	public Films(String name,String trailerPath,int duration) {
		this.name = name;
		this.trailerPath = trailerPath;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrailerPath() {
		return trailerPath;
	}
	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Films)) {
            return false;
        }

        Films u = (Films) o;

        return getName().equals(u.getName());
    }
	@Override
	public String toString() {
		return getName();
	}

	public ArrayList<Halls> getHallList() {
		return hallList;
	}

	public void setHallList(ArrayList<Halls> hallList) {
		this.hallList = hallList;
	}
	
}
