
public class Vertical extends MathJewel {

	public Vertical(int[] location) {
		super(location);
		setSymbol("|");
		//Specific directions for |
		setDirections(new int[][][] {two,eight});
	}

}
