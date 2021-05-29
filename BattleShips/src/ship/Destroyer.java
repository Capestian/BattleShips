package ship;

public class Destroyer extends AbstractShip {
	public Destroyer(String name, Character label, Orientation orientation) {
		super(name, label, 2, orientation);
	}
	public Destroyer(String name, Character label) {
		this(name, label, null);
	}
}
