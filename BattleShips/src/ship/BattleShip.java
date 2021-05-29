package ship;

public class BattleShip extends AbstractShip {
	public BattleShip(String name, Character label, Orientation orientation) {
		super(name, label, 4, orientation);
	}
	public BattleShip(String name, Character label) {
		this(name, label, null);
	}
}
