

public class T extends Jewels{

	public T(int[] location) {
		super(location);
		setSymbol("T");
		//Specific directions for T
		setDirections(new int[][][] {two,eight});
	}
} 
