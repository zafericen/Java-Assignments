import java.util.ArrayList;

public abstract class Jewels {
	private String symbol = "";
	private int[][][] directions;
	//all basic directions for easy use
	public static final int[][] one ={{-1,-1},{-2,-2}};
    public static final int[][] nine ={{1,1},{2,2}};
    public static final int[][] three ={{-2,2},{-1,1}};
    public static final int[][] seven ={{1,-1},{2,-2}};
    public static final int[][] four={{0,-1},{0,-2}};
    public static final int[][] six={{0,1},{0,2}};
    public static final int[][] two={{-2,0},{-1,0}};
    public static final int[][] eight={{1,0},{2,0}};
    //location in the grid
	private int[] location = new int[2];
	//all points of every jewel
	public static final String[][] points = {{"D","30"},{"S","15"},{"W","10"},{"-","20"},{"+","20"},{"\\","20"},
			{"/","20"},{"|","20"},{"T","15"}};
	
	public Jewels(int[] location) {
		setLocation(location);
	}
	//takes jewelgrid to find neighbors
	public ArrayList<Jewels> findNeighbours(Jewels[][] jewelgrid){
		//returns arraylist of jewels that contains same neighbor jewels
		//if there is no neighbor returns null
		ArrayList<Jewels> sameJewels = new ArrayList<Jewels>();
		for (int[][] i : getDirections() ){
			try {
				Jewels jewel1 = jewelgrid[getLocation()[0]+i[0][0]][getLocation()[1]+i[0][1]];
				Jewels jewel2 = jewelgrid[getLocation()[0]+i[1][0]][getLocation()[1]+i[1][1]];
				if (jewel1.getSymbol().equals(getSymbol()) && jewel2.getSymbol().equals(getSymbol())) {
					sameJewels.add(jewel1);
					sameJewels.add(jewel2);
					sameJewels.add(this);
					return sameJewels;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return null;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int[] getLocation() {
		return location;
	}
	public void setLocation(int[] location) {
		this.location = location;
	}
	public int[][][] getDirections() {
		return directions;
	}
	public void setDirections(int[][][] directions) {
		this.directions = directions;
	}
	
}
