package ship;

public enum Orientation {
	NORTH(0, 'n'), EAST(1, 'e'), SOUTH(2, 's'), WEST(3, 'w');

	private int value;
	private Character label;
	
	private Orientation(int value, Character label) {
		this.value = value;
		this.label = label;
	}

    /**
     * @return the label of the orientation
     */
	public Character getlabel() {
		return label;
	}
	
    /**
     * Return the orientation according to the label
     * @param label
     * @return orientation
     */
	public static Orientation fromCharacter(Character label) {
		for(Orientation o : Orientation.values())
			if(o.getlabel() == label)
				return o;
		return null;
	}
	
    /**
     * Return the orientation according to the value
     * @param value
     * @return orientation
     */
	public static Orientation fromInt(int value) {
		for (Orientation orentation : Orientation.values())
			if (orentation.value == value)
				return orentation;
		return null;
	}
}
