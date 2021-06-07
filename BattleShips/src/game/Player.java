package game;

import java.util.List;

import ship.Ship;

public class Player {
	protected Board board;
	protected Board opponentBoard;
	protected int destroyedCount;
	protected Ship[] ships;

	public Player(Board board, Board opponentBoard, List<Ship> ships) {
		this.board = board;
		this.ships = ships.toArray(new Ship[0]);
		this.opponentBoard = opponentBoard;
		this.destroyedCount = 0;
	}

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;
		do {
			Ship s = ships[i];
			String msg = String.format("placer %d : %s(%d), sous la forme suivante: 'A0 n'", i + 1, s.getName(), s.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();
			
			try {
				s.setOrientation(res.orientation);
				board.putShip(s, res.x, res.y);
				done = ++i >= ships.length;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Impossible de placer le navire a cette position");
			}
			board.print();
		} while (!done);
	}

	public Hit sendHit(int[] coords) {
		boolean done = false;
		Hit hit = null;
		do {
			System.out.println("A quelles coordonnées voulez-vous frapper ?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
			coords[0] = hitInput.x;
			coords[1] = hitInput.y;
			if(coords[0] < 0 && coords[0] >= this.opponentBoard.getSize() && coords[1] < 0 && coords[1] >= this.opponentBoard.getSize())
				System.out.println("Impossible de tirer à ces coordonnées");
			else if(opponentBoard.getHit(hitInput.x, hitInput.y) != null)
				System.out.println("Cette zone a déjà été touchée");
			else {
				hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
				done = true;
			}
		} while (!done);
		return hit;
	}

	public Ship[] getShips() {
		return ships;
	}

	public void setShips(Ship[] ships) {
		this.ships = ships;
	}
	
	public void loseAShip() {
		destroyedCount++;
	}
	
	public boolean hasLost() {
		return destroyedCount >= ships.length;
	}
}
