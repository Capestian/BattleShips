package ship;

public class Submarine extends AbstractShip {
	public Submarine(String name, Character label, int length, Orientation orientation) {
		super(name, label, length, orientation);
	}
	public Submarine(String name, Character label, int length) {
		this(name, label, length, null);
	}
}
