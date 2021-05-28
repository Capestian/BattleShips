package ship;

public class Destroyer extends AbstractShip {
	public Destroyer(String name, Character label, int length, Orientation orientation) {
		super(name, label, length, orientation);
	}
	public Destroyer(String name, Character label, int length) {
		this(name, label, length, null);
	}
}
