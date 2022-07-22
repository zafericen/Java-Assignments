
public class Companies extends Properties{

	public Companies(String name, int location, int cost) {
		super(name, location, cost);
	}
	//calculating rent for companies
	@Override
	public int getRent() {
		return getRolledDice()*4;
	}

}
