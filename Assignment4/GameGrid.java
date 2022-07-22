import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class GameGrid {
	//the jewel grid
	public Jewels[][] jewelGrid;
	

	public Jewels[][] getJewelGrid() {
		return jewelGrid;
	}
	public void setJewelGrid(Jewels[][] jewelGrid) {
		this.jewelGrid = jewelGrid;
	}
	//method that takes position from txt
	public int takeAction(int y, int x) {
		//removes jewel and its neighbors at that position and updates map and score and returns it.
		int score = 0;
		ArrayList<Jewels> removeJewels  = jewelGrid[y][x].findNeighbours(jewelGrid);
		if (!Objects.isNull(removeJewels)){
			score += removeNeighbors(removeJewels);	
		}
		fillBlanks();
		return score;
	}
	//takes jewel that in the grid 
	public int removeNeighbors(ArrayList<Jewels> removeJewels) {
		//removes its neighbors and returns score of them
		int score = 0;
		for (Jewels j : removeJewels) {
			
			for (String[] c : Jewels.points) {
				if (jewelGrid[j.getLocation()[0]][j.getLocation()[1]].getSymbol().equals(c[0])) {
					score += Integer.parseInt(c[1]);
				}
			}
			jewelGrid[j.getLocation()[0]][j.getLocation()[1]] = null;
		}
		return score;
	}
	//updates grid 
	public void fillBlanks(){
        //it pushes jewels to lower lever if they have space underneath them 
		for(int control=0;control<9;control++) {
            for (int y = jewelGrid.length-1; y > 0; y--) {
                for (int x = 0; x < jewelGrid[0].length; x++) {
                    if ( Objects.isNull(jewelGrid[y][x])) {
                        jewelGrid[y][x] = jewelGrid[y - 1][x];
                        if(!Objects.isNull(jewelGrid[y][x])) {
                        	jewelGrid[y][x].setLocation(new int[] {y,x});
                        }
                        jewelGrid[y - 1][x] = null;
                    }
                }
            }
        }
    }
	//prints current state of the grid
	public String printGrid() {
	    String returnString = "\n";    
		for(Jewels[] row :jewelGrid){
	            for(Jewels jewel: row){
	                if (Objects.isNull(jewel)) {
						returnString += "  ";
					}
	                else {
						returnString += jewel.getSymbol()+" ";
					}
	            }
	            returnString += "\n";
	        }
	        returnString += "\n";
	    return returnString;
	}
	//takes path of the txt file
	public void initializeGrid(String path) throws FileNotFoundException {
		//initialize jewel grid according to txt file
		File file2 = new File(path);
		Scanner sc_grid = new Scanner(file2);
		sc_grid.useDelimiter("\\Z");
			
		ArrayList<String> lineList = new ArrayList<String>();
			
		while (sc_grid.hasNextLine()) {
			lineList.add(sc_grid.nextLine());
		}
			
		//initial game grid
		setJewelGrid(new Jewels[lineList.size()][lineList.get(0).split(" ").length]);
		for(int y = 0 ; y<lineList.size();y++  ) {
			String[] xLine = lineList.get(y).split(" ");
			for (int x = 0;x<xLine.length ;x++) {
				String string = xLine[x];
				switch (string) {
					case "D": {
						D d = new D(new int[] {y,x});
						jewelGrid[y][x] = d;
						break;
					}
					case "S": {
						S s = new S(new int[] {y,x});
						jewelGrid[y][x] = s;
						break;
					}
					case "T": {
						T t = new T(new int[] {y,x});
						jewelGrid[y][x] = t;
						break;
					}
					case "W": {
						W w = new W(new int[] {y,x});
						jewelGrid[y][x] = w;
						break;
					}case "\\":{
						BackSlash backSlash = new BackSlash(new int[] {y,x});
						jewelGrid[y][x] = backSlash;
						break;
					}case "-":{
						Minus minus = new Minus(new int[] {y,x});
						jewelGrid[y][x] = minus;
						break;
					}case "+":{
						Plus plus = new Plus(new int[] {y,x});
						jewelGrid[y][x] = plus;
						break;
					}case "/":{
						Slash slash = new Slash(new int[] {y,x});
						jewelGrid[y][x] = slash;
						break;
					}case "|":{
						Vertical vertical = new Vertical(new int[] {y,x});
						jewelGrid[y][x] = vertical;
						break;
					}
				}
					
			}
		}
		sc_grid.close();
	}
	
	//takes txt file's path
	public ArrayList<Object> readCommands(String path) throws FileNotFoundException {
		//returns arraylist of 2 object:1 is a player object that appeared in the final line of the xommand file
		//2 is a string that will be used for writing grid
		ArrayList<Object> returnList = new ArrayList<Object>();
		String returnString = "";
		
		File file1 = new File(path);
		Scanner sc_command = new Scanner(file1);
		sc_command.useDelimiter("\\Z");
		
		returnString += "Game Grid:\n";
	    returnString += printGrid();
	    int totalScore = 0;
	    int score = 0;
	    while (sc_command.hasNextLine()) {
	    	String[] commandStrings = sc_command.nextLine().split(" ");
	    	if (commandStrings[0].equals("E")) {
	    		returnString += "Select coordinate or enter E to end the game: E\n";
	    		break;
			}
	    	returnString += "Select coordinate or enter E to end the game: " + commandStrings[0]
	    			+ " "+ commandStrings[1] + "\n";
	    	try {
	    		score = takeAction(Integer.parseInt(commandStrings[0]), Integer.parseInt(commandStrings[1]));
			} catch (NullPointerException e) {
				returnString +="\nPlease enter a valid coordinate\n\n";
				continue;
			}
	    	
			totalScore += score;
	    	returnString += printGrid();
			returnString += "Score: "+score+"\n\n";
		}
	    returnString += "\nTotal score: "+totalScore+"\n";
	    
	    Players commandPlayer = new Players(sc_command.nextLine(), totalScore);
	    returnString += "\nEnter name: " + commandPlayer.getName()+"\n\n";
	    returnList.add(commandPlayer);
	    returnList.add(returnString);
	    sc_command.close();
	    return returnList;
	    
	}
}
