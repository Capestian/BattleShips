package game;

import ship.Ship;

public class ShipState {
	private Ship ship;
	private boolean struck;
	
	public ShipState(Ship ship) {
		this.ship = ship;
		struck = false;
	}
	
	public Ship getShip() {
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