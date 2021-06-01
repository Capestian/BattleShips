package game;

import ship.AbstractShip;

public class ShipState {
	private AbstractShip ship;
	private boolean struck;
	
	public ShipState() {
		struck = false;
	}
	
	public void setShip(AbstractShip ship) {
		this.ship = ship;
	}
	
	public AbstractShip getShip() {
		return ship;
	}
	
	public boolean isStruck() {
		return struck;
	}
	
	public void addStrike() {
		if(!struck) {
			struck = true;
			ship.addStrike();
		}
	}
	
	public boolean isSunk() {
		return ship.isSunk();
	}

	@Override
	public String toString() {
		if(ship == null)
			return "" + Board.seaChar;
		if(isStruck())
			return ("" + ship.getLabel()).toUpperCase();
		else
			return "" + ship.getLabel();
	}
}