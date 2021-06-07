package ship;

public class Ship {
	private String name;
	private Character label;
	private int length;
	private int strikeCount;
	private Orientation orientation;

	private Ship(String name, Character label, int length, Orientation orientation) {
		this.name = name;
		this.label = label;
		this.length = length;
		this.orientation = orientation;
	}
	
	public static Ship getDestroyer() {
		return new Ship("Frégate", 'f', 2, null);
	}
	
	public static Ship getSubmarine() {
		return new Ship("Sous-marin", 's', 3, null);
	}
	
	public static Ship getBattleShip() {
		return new Ship("Croiseur", 'c', 4, null);
	}
	
	public static Ship getCarrier() {
		return new Ship("Porte-avion", 'p', 5, null);
	}

	public String getName() {
		return name;
	}
	public Character getLabel() {
		return label;
	}
	public int getLength() {
		return length;
	}
	
	public void addStrike() {
		strikeCount++;
	}
	
	public boolean isSunk() {
		return strikeCount >= length;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
}
