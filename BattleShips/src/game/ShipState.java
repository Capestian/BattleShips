package game;

import ship.AbstractShip;

public class ShipState {
	private AbstractShip ship;
	private boolean struck;
	
	public ShipState(AbstractShip ship) {
		this.ship = ship;
		struck = false;
	}
	
	public AbstractShip getShip() {
		return ship;
	}
	
	public boolean isStruck() {
		return struck;
	}
	
	public void addStrike() {
		if(ship != null && !struck) {
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