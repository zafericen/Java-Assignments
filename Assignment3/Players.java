

public class Players implements Comparable<Players>{
	private String name = "";
	private int score = 0;
	
	public Players(String name, int score) {
		setName(name);
		setScore(score);
	}
	//overriding the compareTo method
	@Override
	public int compareTo(Players o) {
		if (getScore() == o.getScore()) {
			return 0;
		}else if (getScore()>o.getScore()) {
			return 1;
		}
		return -1;
	}
	//overriding the equals method
	@Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }

        if (!(o instanceof Jewels)) {
            return false;
        }

        Players p = (Players) o;

        return  getName().equals(p.getName()) && getScore() == p.getScore();
    }
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



}
