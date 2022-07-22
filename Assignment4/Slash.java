
public class Slash extends MathJewel {

	public Slash(int[] location) {
		super(location);
		setSymbol("/");
		//Specific directions for /
		setDirections(new int[][][] {three,seven});
	}

}
