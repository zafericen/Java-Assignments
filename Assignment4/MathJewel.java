import java.util.ArrayList;

public abstract class MathJewel extends Jewels {

	public MathJewel(int[] location) {
		super(location);
	}
	//takes jewelgrid to find math symbol neighbors
	public ArrayList<Jewels> findNeighbours(Jewels[][] jewelgrid){
		//returns arraylist of jewels that contains same neighbor jewels
		//if there is no neighbor returns null
		ArrayList<Jewels> sameJewels = new ArrayList<Jewels>();
		for (int[][] i : getDirections() ){
			try {
				Jewels jewel1 = jewelgrid[getLocation()[0]+i[0][0]][getLocation()[1]+i[0][1]];
				Jewels jewel2 = jewelgrid[getLocation()[0]+i[1][0]][getLocation()[1]+i[1][1]];
				if (jewel1 instanceof MathJewel && jewel2 instanceof MathJewel) {
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
	

}
