
public class Jail extends InteractiveSquares{
	public Jail(String name, int location) {
		super(name, location);
		setWaitTurns(3);
	}
	//for output string
	@Override
	public  String getRoundString() {
		String s = " in jail ";
		return s;
	}
	
}
