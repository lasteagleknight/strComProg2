package models;

public class AnchorPoint extends Point {

	private int region;

	public AnchorPoint(int posX, int posY, int region) {
		super(posX, posY);

		this.region = region;
		// TODO Auto-generated constructor stub
	}

	public int getValue() {
		return region;
	}

}
