import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		//Strings for writers
		String monitoringString = "";
		String leaderboardString = "";
	    //creating leaderboard object and initializing players
	    LeaderBoard leaderBoard = new LeaderBoard();
	    leaderBoard.initializePlayers("leaderboard.txt");
	    //creating monitoring.txt file
	    File outputfile= new File("monitoring.txt");
	    FileWriter writer_grid=new FileWriter("monitoring.txt");
	    //creating leaderboard.txt file
	    File outputFile2 = new File("leaderboard.txt");
	    FileWriter writer_board=new FileWriter("leaderboard.txt");
	    //creating gameGrid object and initializing it
	    GameGrid gameGrid = new GameGrid();
	    gameGrid.initializeGrid(args[0]); 
	    //getting an list of 2 objects
	    ArrayList<Object> returnList = gameGrid.readCommands(args[1]);
	    //getting last player that appeard in txt from that list
	    Players commandPlayer = (Players)returnList.get(0);
	    //getting grid String from that list too
	    monitoringString += (String) returnList.get(1);
	    //adding player to both player list
	    leaderBoard.playerList.add(commandPlayer);
	    leaderBoard.rawPlayerList.add(commandPlayer);
	    //adding last strings to final strings
	    monitoringString += leaderBoard.boardQuee(commandPlayer) +"\nGood bye!";
	    leaderboardString += leaderBoard.printLeaderBoard();
	    //writing final strings to txt files and closing writers
	    writer_grid.write(monitoringString);
	    writer_board.write(leaderboardString);
	    writer_grid.close();
	    writer_board.close();
	}
}