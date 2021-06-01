package ship;

public abstract class AbstractShip {
	// Constants:
	// Attributes:
	@SuppressWarnings("unused")
	private String name;
	private Character label;
	private int length;
	private Orientation orientation;

	public AbstractShip(String name, Character label, int length, Orientation orientation) {
		this.name = name;
		this.label = label;
		this.length = length;
		this.orientation = orientation;
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
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
}
