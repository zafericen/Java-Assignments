

public class D extends Jewels{
	
	public D(int[] location) {
		super(location);
		setSymbol("D");
		//Specific directions for D
		setDirections(new int[][][] {one,nine,three,seven});
	}
} 
