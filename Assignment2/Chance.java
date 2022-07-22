import java.util.ArrayList;

public class Chance extends ActionSquares{
	public static ArrayList<Cards> cardListChance = new ArrayList<Cards>();

	public Chance(String name, int location) {
		super(name, location);
	}
	//getting top chance card
	@Override
	public Cards getTopCard() {
		Cards card = cardListChance.get(0);
		cardListChance.remove(0);
		cardListChance.add(card);
		return card;
	}
	
}
