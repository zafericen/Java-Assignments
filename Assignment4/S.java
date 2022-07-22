

public class S extends Jewels{

	public S(int[] location) {
		super(location);
		setSymbol("S");
		//Specific directions for S
		setDirections(new int[][][] {four,six});
	}
} 
