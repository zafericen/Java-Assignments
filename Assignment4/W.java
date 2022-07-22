import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class W extends Jewels{

	public W(int[] location) {
		super(location);
		setSymbol("W");
		//Specific directions for W
		setDirections(new int[][][] {two,eight,four,six,one,nine,three,seven});
	}
	//spesific method for w to find neighbors
	@Override
	public ArrayList<Jewels> findNeighbours(Jewels[][] jewelgrid) {
		//returns arraylist of jewels that contains same neighbor jewels
		//if there is no neighbor returns null
		ArrayList<Jewels> sameJewels = new ArrayList<Jewels>();
		for (int[][] i : getDirections() ){
			try {
				Jewels jewel1 = jewelgrid[getLocation()[0]+i[0][0]][getLocation()[1]+i[0][1]];
				Jewels jewel2 = jewelgrid[getLocation()[0]+i[1][0]][getLocation()[1]+i[1][1]];
				String[] tempStrings = {jewel1.getSymbol(),jewel2.getSymbol(),getSymbol()};
				ArrayList<String> compareStrings = new ArrayList<String>(Arrays.asList(tempStrings));
				if (Collections.frequency(compareStrings,compareStrings.get(0))>= 2||
						Collections.frequency(compareStrings,"W")>= 2) {
					sameJewels.add(jewel1);
					sameJewels.add(jewel2);
					sameJewels.add(this);
					return sameJewels;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}catch (NullPointerException e) {
				continue;
			}
		}
		return null;
	}

}
