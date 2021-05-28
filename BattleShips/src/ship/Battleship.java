package ship;

public class Battleship extends AbstractShip {
	public Battleship(String name, Character label, int length, Orientation orientation) {
		super(name, label, length, orientation);
	}
	public Battleship(String name, Character label, int length) {
		this(name, label, length, null);
	}
}
