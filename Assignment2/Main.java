import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class Main {
	//fields checking line endings
	static boolean control = false;
	static String command = "";
	
	public static void main(String[] args) throws IOException, ParseException {
		
		//creating board
		GamePlay.crateBoard();
		//creating players
		Players player1 = new Players("Player 1", 15_000);
		Players player2 = new Players("Player 2", 15_000);
		Banker banker = new Banker("Banker", 100_000);
		//setting static variables for different classes
		Cards.banker = banker;
		Cards.player1 = player1;
		Cards.player2 = player2;
		
		GamePlay.player1s = player1;
		GamePlay.player2s = player2;
		
		Squares.banker = banker;
		
		Players.banker = banker;
		//opening input file
		File file = new File("command.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		//creating output file
		File outputfile= new File("output.txt");
	    FileWriter writer=new FileWriter("output.txt");
		//adding json data
	    GamePlay.initializeJson("property.json", "list.json", banker);
		//reading input file
		while (sc.hasNextLine() || control) {
			//Controlling game's end conditions
			if ((GamePlay.endGame(player1, player2, banker) || control)){
				if (!command.equals("show()")) {
					GamePlay.show(player1, player2, banker);	
				}
				break;
			}
			command = sc.nextLine();
			control = !sc.hasNextLine();
			
			//show method
			if(command.equals("show()")) {
				GamePlay.show(player1, player2, banker);
			}
			else {
				String[] commands = command.split(";");
				if (commands[0].equals(player1.getName())) {
					//moving player 1
					GamePlay.rollDice(player1, Integer.parseInt(commands[1]));
				}else {
					//moving player 2
					GamePlay.rollDice(player2, Integer.parseInt(commands[1]));
				}
			}		
		}
		//writing final string and closing reader and writer
		writer.write(GamePlay.finalString);
		writer.close();
		sc.close();
	}
}
