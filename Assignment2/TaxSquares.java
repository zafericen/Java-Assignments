
public class TaxSquares extends InteractiveSquares{
	
	public TaxSquares(String name, int location) {
		super(name, location);
		setGiveAmount(-100);
	}
	//getting money change amount
	@Override
	public int getGiveAmount() {
		banker.setMoney(super.getGiveAmount()*-1);
		return super.getGiveAmount();
	}
	
}
