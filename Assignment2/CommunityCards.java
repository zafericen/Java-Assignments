
public class CommunityCards extends Cards{
		
	public CommunityCards(String name, int moneyChange, int positionChange,
			// effectOnOther is a variable that keeps the data of card's effect on other player
			int addChange,boolean effectOnOther) {
		super(name, moneyChange, positionChange, addChange);
		setEfectOnOther(effectOnOther);
	}

}
