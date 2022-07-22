import java.util.ArrayList;

public class CommunityChest extends ActionSquares{
	public static ArrayList<Cards> cardListCommunity = new ArrayList<Cards>();

	public CommunityChest(String name, int location) {
		super(name, location);
	}
	//getting top community card
	@Override
	public Cards getTopCard() {
		Cards card = cardListCommunity.get(0);
		cardListCommunity.remove(0);
		cardListCommunity.add(card);
		return card;
	}
	
}
