
public abstract class InteractiveSquares extends Squares{
	//setting variables for effecting player that landed on that square
	private int giveAmount;
	private int waitTurns;

	public InteractiveSquares(String name, int location) {
		super(name, location);
	}
	public int getGiveAmount() {
		return giveAmount;
	}
	public void setGiveAmount(int giveAmount) {
		this.giveAmount = giveAmount;
	}
	public int getWaitTurns() {
		return waitTurns;
	}
	public void setWaitTurns(int waitTurns) {
		this.waitTurns = waitTurns;
	}
	// for more elastic writing
	public String getRoundString() {
		return "";
	}

}
