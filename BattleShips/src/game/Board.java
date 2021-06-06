package game;

import game.InputHelper.ShipInput;
import ship.AbstractShip;
import ship.BattleShip;
import ship.Carrier;
import ship.Destroyer;
import ship.Submarine;

public class Board implements IBoard {
	// Constants:
	// private static String retourLigne = System.getProperty("line.separator");
	public static int defaultSize = 10;
	public static int maximunSize = 26;
	public static Character seaChar = '.';
	public static Character hitChar = 'x';

	// Attributes:
	private String name;
	private int size;
	private ShipState[][] ships;
	private Hit[][] hits;

	public Board(String name, int size) {
		this.name = name;
		if (size > maximunSize)
			this.size = maximunSize;
		else
			this.size = size;
		this.ships = new ShipState[this.size][this.size];
		this.hits = new Hit[this.size][this.size];
	}

	public Board(String name) {
		this(name, defaultSize);
	}
	
	/**
	 * Print the board
	 */
	public void print() {
		StringBuffer s = new StringBuffer();
		s.append(name + " :\n");
		s.append("Navires :");
		if (size > 2) {
			for (int i = 2; i < size; i++) {
				s.append("  ");
			}
		}
		s.append("Frappes :\n ");
		if (size >= 10)
			s.append(" ");
		for (char a = 'A'; a < 'A' + size; a++) {
			s.append(" " + a);
		}
		s.append("     ");
		if (size >= 10)
			s.append(" ");
		for (char a = 'A'; a < 'A' + size; a++) {
			s.append(" " + a);
		}
		s.append("\n");
		for (int i = 0; i < size; i++) {
			if (i < 9)
				s.append(" ");
			s.append(i + 1);
			for (int k = 0; k < size; k++) {
				if (ships[k][i] != null)
					s.append(" " + ships[k][i]);
				else
					s.append(" " + seaChar);
			}
			s.append("    ");
			if (i < 9)
				s.append(" ");
			s.append(i + 1);
			for (int k = 0; k < size; k++) {
				if (hits[k][i] != null)
					s.append(" " + hitChar);
				else
					s.append(" " + seaChar);
			}
			s.append("\n");
		}
		System.out.println(s.toString());
	}

	/**
	 * @return the size of the Board
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * put the given ship at the given coord
	 *
	 * @param ship The ship to place on the board
	 * @param x The abscissa
	 * @param y The ordinate
	 * @throws BoardException
	 */
	@Override
	public void putShip(AbstractShip ship, int x, int y) throws BoardException {
		if (x < 0 && x >= size && y < 0 && y >= size)
			throw new BoardException();
		switch (ship.getOrientation()) {
		case NORTH:
			if (y - ship.getLength() + 1 < 0)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if (ships[x][y - i] != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x][y - i] = new ShipState(ship);
			break;
		case EAST:
			if (x + ship.getLength() - 1 >= size)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if (ships[x + i][y] != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x + i][y] = new ShipState(ship);
			break;
		case SOUTH:
			if (y + ship.getLength() - 1 >= size)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if (ships[x][y + i] != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x][y + i] = new ShipState(ship);
			break;
		case WEST:
			if (x - ship.getLength() + 1 < 0)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if (ships[x - i][y] != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x - i][y] = new ShipState(ship);
			break;
		}
	}

	/**
	 * @param x The abscissa
	 * @param y The ordinate
	 * @return true if a ship is located at the given coords
	 */
	@Override
	public boolean hasShip(int x, int y) {
		return ships[x][y] != null;
	}

	/**
	 * Set the state of the hit at a given position
	 *
	 * @param hit true if the hit must be set to successful
	 * @param x The abscissa
	 * @param y The ordinate
	 */
	@Override
	public void setHit(Hit hit, int x, int y) {
		if (ships[x][y] != null)
			ships[x][y].addStrike();
		hits[x][y] = hit;
	}

	/**
	 * Get the state of a hit at the given position
	 *
	 * @param x The abscissa
	 * @param y The ordinate
	 * @return
	 */
	@Override
	public Hit getHit(int x, int y) {
		return hits[x][y];
	}

	/**
	 * Sends a hit at the given position
	 * 
	 * @param x The abscissa
	 * @param y The ordinate
	 * @return status for the hit (eg : strike or miss)
	 */
	@Override
	public Hit sendHit(int x, int y) {
		Hit hit = null;
		if (ships[x][y] == null)
			hit = Hit.MISS;
		else {
			ships[x][y].addStrike();
			AbstractShip ship = ships[x][y].getShip();
			if (!ship.isSunk())
				hit = Hit.STIKE;
			else {
				if (ship instanceof Destroyer)
					hit = Hit.DESTROYER;
				if (ship instanceof Submarine)
					hit = Hit.SUBMARINE;
				if (ship instanceof BattleShip)
					hit = Hit.BATTLESHIP;
				if (ship instanceof Carrier)
					hit = Hit.CARRIER;
			}
		}
		hits[x][y] = hit;
		return hit;
	}

	/**
	 * test the class Board
	 */
	public static void testBoard() {
		Board board = new Board("Bataille navale", 12);
//		AbstractShip[] ships = { new Destroyer("Destroyer", 'd'), new Submarine("Submarine A", 's'), new Submarine("Submarine B", 's'), new BattleShip("BattleShip", 'b'), new Carrier("Carrier", 'c') };
		AbstractShip[] ships = { new Destroyer("Destroyer", 'd') };
		int i = 0;
		boolean done = false;
		board.print();
		do {
			AbstractShip s = ships[i];
			System.out.println("Entrez l'emplacement du navire '" + s.getName() + "', sous la forme suivante: 'A0 n'");
			ShipInput res = InputHelper.readShipInput();
			try {
				s.setOrientation(res.orientation);
				board.putShip(s, res.x, res.y);
				done = ++i == ships.length;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Impossible de placer le navire a cette position");
			}
			board.print();
		} while (!done);
		board.sendHit(1, 0);
		System.out.println(ships[0].isSunk());
		Hit hit = board.sendHit(0, 0);
		if (hit == Hit.DESTROYER)
			System.out.println("Le " + ships[0].getName() + " est coulé.");
		board.sendHit(5, 5);
		board.print();
	}

	public static void main(String[] args) {
		testBoard();
	}
}