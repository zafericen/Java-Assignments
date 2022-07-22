
public class BackSlash extends MathJewel {

	public BackSlash(int[] location) {
		super(location);
		setSymbol("\\");
		//Specific directions for \
		setDirections(new int[][][] {one,nine});
		
	}

}
