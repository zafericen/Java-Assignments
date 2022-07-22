
public class GoSquare extends InteractiveSquares{
	public GoSquare(String name, int location) {
		super(name, location);
		setGiveAmount(200);
	}
	
	//overriding method to use properly
	@Override
	public int getGiveAmount() {
		banker.setMoney(super.getGiveAmount()*-1);
		return super.getGiveAmount();
		
	}
	
}
