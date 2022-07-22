
public class FreeParking extends InteractiveSquares{
	public FreeParking(String name, int location) {
		super(name, location);
		setWaitTurns(1);
	}
	//getting string output
	@Override
	public String getRoundString() {
		String s = " is in FreeParking ";
		return s;
	}
}
