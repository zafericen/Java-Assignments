import java.util.ArrayList;

public abstract class ActionSquares extends Squares{
	public ArrayList<Cards> cardList = new ArrayList<Cards>();
	public ActionSquares(String name, int location) {
		super(name, location);
	}
	//for drawing the top card end putting it end of stack
	public abstract Cards getTopCard();
	
	
}
	
