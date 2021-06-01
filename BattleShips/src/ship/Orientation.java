package ship;

public enum Orientation {
	NORTH('n'), EAST('e'), SOUTH('s'), WEST('w');
	
	private Character label;
	
	private Orientation(Character label) {
		this.label = label;
	}
	
	public Character getlabel() {
		return label;
	}
	
	public static Orientation getOrientation(Character label) {
		for(Orientation o : Orientation.values())
			if(o.getlabel() == label)
				return o;
		return null;
	}
}
