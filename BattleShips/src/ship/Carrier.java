package ship;

public class Carrier extends AbstractShip {
	public Carrier(String name, Character label, Orientation orientation) {
		super(name, label, 5, orientation);
	}
	public Carrier(String name, Character label) {
		this(name, label, null);
	}
}