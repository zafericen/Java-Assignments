import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GamePlay {
	//final string that will be print to txt file
	static String finalString = "";
	//static users for printing money in same order(player1,player2)
	static Players player1s;
	static Players player2s;
	//card values for changing its variables while we constructing them
	static ArrayList<Integer[]> cardValues = new ArrayList<Integer[]>();
	
	public static boolean endGame(Players player1,Players player2,Banker banker){
		//Controlling money situations and game's end status
		if (player1.getMoney() <= 0 ) {
			String roundString = player1.getName() + "\t" + player1.getLastDice() + "\t" + 
					player1.getLocation() + "\t" + player1.getMoney() + "\t" + player2.getMoney();
			roundString = roundString + "\t" + player1.getName() + " goes bankrupt";
			finalString = finalString + roundString + "\n";
			return true;
		}
		else if (player2.getMoney() <= 0 ) {
			String roundString = player2.getName() + "\t" + player2.getLastDice() + "\t" + 
					player2.getLocation() + "\t" + player1.getMoney() + "\t" + player2.getMoney();
			roundString = roundString + "\t" + player2.getName() + " goes bankrupt";
			finalString = finalString +  roundString + "\n";
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void show(Players player1,Players player2,Banker banker) {
		//the show method that shows us game's current situation
		String roundString = new String(new char[107]).replace("\0", "-") +"\n";
		
		roundString = roundString + player1.getName() + "\t" + 
				player1.getMoney() + "\t" + "have:"+player1.getPropertiesString() + "\n";
		
		roundString = roundString + player2.getName() + "\t" + 
				player2.getMoney() + "\t" + "have:"+player2.getPropertiesString() + "\n";
		
		roundString = roundString + banker.getName() + "\t" + banker.getMoney() + "\n";
		
		String winner=player1.getMoney()>player2.getMoney() ? player1.getName():player2.getName();
		
		roundString = roundString + "Winner:" + winner + "\n";
		roundString = roundString + new String(new char[107]).replace("\0", "-") + "\n";
		
		finalString = finalString + roundString ;
	}
	public static void rollDice(Players player, int dice) {
		//rollDice method to roll and move specific player
		player.setLastDice(dice);
		player.rollLastDice();
		String roundString = player.getName() + "\t" + player.getLastDice() + "\t" + 
		player.getLocation() + "\t" + player1s.getMoney() + "\t" + player2s.getMoney();
		roundString = roundString + "\t" +player.roundString;
		finalString = finalString + roundString + "\n";
		
	}
	public static void crateBoard() {
		//initializing board's non-json squares and adding card values to list
		Integer[] moneyChanges = {0,0,0,-15,150,100,0,75,-50,10,50,20,100,-100,-50,100,50};
		Integer[] positionChanges = {0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer[] addChanges = {1,27,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		Integer[] effectAnother = {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0};
		cardValues.add(moneyChanges);
		cardValues.add(positionChanges);
		cardValues.add(addChanges);
		cardValues.add(effectAnother);
		
		Squares.arrOfSquares.add(new GoSquare("Go", 1));
		Squares.arrOfSquares.add(new Jail("Jail", 11));
		Squares.arrOfSquares.add(new FreeParking("Free Parking", 21));
		Squares.arrOfSquares.add(new GoToJail("Go to Jail", 31));
		Squares.arrOfSquares.add(new TaxSquares("Income Tax", 5));
		Squares.arrOfSquares.add(new TaxSquares("Super Tax", 39));

		Squares.arrOfSquares.add(new CommunityChest("CommunityChest", 3));
		Squares.arrOfSquares.add(new CommunityChest("CommunityChest", 18));
		Squares.arrOfSquares.add(new CommunityChest("CommunityChest", 34));
		
		Squares.arrOfSquares.add(new Chance("Chance", 8));
		Squares.arrOfSquares.add(new Chance("Chance", 23));
		Squares.arrOfSquares.add(new Chance("Chance", 37));
		
	}
	public static void initializeJson(String propertyFile,String listFile,Banker banker) 
			throws IOException, ParseException {
		//adding rest of squares and cards to arrays with json
		JSONParser parser = new JSONParser();
		
		FileReader propertiesFileReader = new FileReader(propertyFile);
		Object object = parser.parse(propertiesFileReader);
		JSONObject jsonProperty = (JSONObject) object;
		
		for (Object property:(JSONArray) jsonProperty.get("1")) {
			Lands lands = new Lands((String)((JSONObject)property).get("name"), 
					Integer.parseInt((String)((JSONObject)property).get("id")), 
					Integer.parseInt((String)((JSONObject)property).get("cost")));
			Squares.arrOfSquares.add(lands);
			banker.addPropertie(lands);
		}for (Object property:(JSONArray) jsonProperty.get("2")) {
			Railroads railroads = new Railroads((String)((JSONObject)property).get("name"), 
					Integer.parseInt((String)((JSONObject)property).get("id")), 
					Integer.parseInt((String)((JSONObject)property).get("cost")));
			Squares.arrOfSquares.add(railroads);
			banker.addPropertie(railroads);
		}for (Object property:(JSONArray) jsonProperty.get("3")) {
			Companies company = new Companies((String)((JSONObject)property).get("name"), 
					Integer.parseInt((String)((JSONObject)property).get("id")), 
					Integer.parseInt((String)((JSONObject)property).get("cost")));
			Squares.arrOfSquares.add(company);
			banker.addPropertie(company);
		}
		FileReader cardsFileReader = new FileReader(listFile);
		Object object2 = parser.parse(cardsFileReader);
		JSONObject jsonCard = (JSONObject) object2;
		
		Squares.arrOfSquares.sort(Comparator.comparing(Squares::getLocation));
		
		int i = 0;
		for (Object card:(JSONArray) jsonCard.get("chanceList")){
			ChanceCards chanceCard = new ChanceCards(
					(String)((JSONObject)card).get("item"),
					cardValues.get(0)[i],
					cardValues.get(1)[i], 
					cardValues.get(2)[i]);
			
			Chance.cardListChance.add(chanceCard);
			i++;
		}for (Object card:(JSONArray) jsonCard.get("communityChestList")){
			boolean a = cardValues.get(3)[i] == 1 ? true:false; 
			CommunityCards communityCard = new CommunityCards(
					(String)((JSONObject)card).get("item"),
					cardValues.get(0)[i],
					cardValues.get(1)[i], 
					cardValues.get(2)[i],a);
			CommunityChest.cardListCommunity.add(communityCard);
			i++;
		}
		
	}

}
