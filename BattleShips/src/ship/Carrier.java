package ship;

public class Carrier extends AbstractShip {
	public Carrier(String name, Character label, int length, Orientation orientation) {
		super(name, label, length, orientation);
	}
	public Carrier(String name, Character label, int length) {
		this(name, label, length, null);
	}
}