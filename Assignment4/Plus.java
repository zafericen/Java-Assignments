
public class Plus extends MathJewel {

	public Plus(int[] location) {
		super(location);
		setSymbol("+");
		//Specific directions for +
		setDirections(new int[][][] {four,six,two,eight});
	}

}
