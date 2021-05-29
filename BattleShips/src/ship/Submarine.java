package ship;

public class Submarine extends AbstractShip {
	public Submarine(String name, Character label, Orientation orientation) {
		super(name, label, 3, orientation);
	}
	public Submarine(String name, Character label) {
		this(name, label, null);
	}
}
