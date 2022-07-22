

public class Railroads extends Properties{
	
	public Railroads(String name, int location, int cost) {
		super(name, location, cost);
	}
	//calculating rent for railroads
	@Override
	public int getRent() {
		int numOfRailroads = 0;
		if (getOwner().getClass() == Banker.class) {
			return 0;
		}
		for(Properties p: getOwner().getArrOfProperties()) {
			
			if (p instanceof Railroads) {
				numOfRailroads++;
			}
		}
		return 25*numOfRailroads;
		
	}

}
