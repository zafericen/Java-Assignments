
public class Minus extends MathJewel {

	public Minus(int[] location) {
		super(location);
		setSymbol("-");
		//Specific directions for -
		setDirections(new int[][][] {four,six});
	}

}
