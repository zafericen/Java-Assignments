import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeaderBoard {
	//player list that will be sorted
	public  ArrayList<Players> playerList = new ArrayList<Players>();
	//player list for printing
	public  ArrayList<Players> rawPlayerList = new ArrayList<Players>();
	
	//takes player to finds its order
	public String boardQuee(Players player) {
		//returns String that contains information about players status
		Collections.sort(playerList,Collections.reverseOrder());
		String returnString = "Your rank is ";
		int index =  playerList.indexOf(player);
		returnString += (index+1) + "/" + (playerList.size())+",";
		try {
			Players highPlayer = playerList.get(index-1);
			try {
				Players lowPlayer = playerList.get(index+1);
				int highScore = highPlayer.getScore()-player.getScore();
				int lowScore = player.getScore()-lowPlayer.getScore();
				returnString += "your score is "+highScore+" points lower than "+highPlayer.getName()+
						" and " + lowScore + " points higher than "+ lowPlayer.getName(); 
			} catch (IndexOutOfBoundsException e) {
				int highScore = highPlayer.getScore()-player.getScore();
				returnString += "your score is "+highScore+"points lower than "+highPlayer.getName();
			}
			
		} catch (IndexOutOfBoundsException e) {
			Players lowPlayer = playerList.get(index+1);
			int lowScore = player.getScore()-lowPlayer.getScore();
			returnString += "your score is "+lowScore + " points higher than "+ lowPlayer.getName();
		}
		return returnString+"\n";
		
		
	}
	
	public String printLeaderBoard() {
		//returns String that contains leaderboard
		String returnString = "";
		for(Players p : rawPlayerList) {
			returnString += p.getName()+" "+p.getScore()+"\n";
		}
		return returnString;
	}
	//takes path of the txt file
	public void initializePlayers(String path) throws FileNotFoundException {
		//initialize all players before game 
		File file3 = new File(path);
		Scanner sc_board = new Scanner(file3);
		sc_board.useDelimiter("\\Z");
		
		while (sc_board.hasNextLine()) {
			String[] commandString = sc_board.nextLine().split(" ");
	    	Players player = new Players(commandString[0], Integer.parseInt(commandString[1]));
	    	playerList.add(player);
	    	rawPlayerList.add(player);
		}
		sc_board.close();
	}
	

}
