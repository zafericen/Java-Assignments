
public class Lands extends Properties{

	public Lands(String name, int location, int cost) {
		super(name, location, cost);
	}
	//calculating rent for lands
	@Override
	public int getRent() {
		if( 0<=getCost() && getCost()<=2000) {
			return (int) (getCost()*0.4);
		}
		else if (2001<=getCost() && getCost()<=3000) {
			return (int) (getCost()*0.3);
		}
		else if (3001<=getCost() && getCost()<=4000) {
			return (int) (getCost()*0.35);
		}
		else {
			return 0;
		}
	}
}
