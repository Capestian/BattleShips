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
	@SuppressWarnings("unused")
	private String name;
	private int size;
	private ShipState[][] ships;
	private Boolean[][] hits;

	public Board(String name, int size) {
		this.name = name;
		if (size > maximunSize)
			this.size = maximunSize;
		else
			this.size = size;
		this.ships = new ShipState[this.size][this.size];
		this.hits = new Boolean[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int k = 0; k < this.size; k++) {
				hits[i][k] = false;
				ships[i][k] = new ShipState();
			}
		}
	}

	public Board(String name) {
		this(name, defaultSize);
	}

	public void print() {
		StringBuffer s = new StringBuffer();
		s.append("Navires :");
		if (size > 3) {
			for (int i = 3; i < size; i++) {
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
				s.append(" " + ships[k][i]);
			}
			s.append("    ");
			if (i < 9)
				s.append(" ");
			s.append(i + 1);
			for (int k = 0; k < size; k++) {
				if (hits[k][i])
					s.append(" " + hitChar);
				else
					s.append(" " + seaChar);
			}
			s.append("\n");
		}
		System.out.println(s.toString());
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void putShip(AbstractShip ship, int x, int y) throws BoardException {
		if (x < 0 && x >= size && y < 0 && y >= size)
			throw new BoardException();
		switch (ship.getOrientation()) {
		case NORTH:
			if (y - ship.getLength() + 1 < 0)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if(ships[x][y - i].getShip() != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x][y - i].setShip(ship);
			break;
		case EAST:
			if (x + ship.getLength() - 1 >= size)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if(ships[x + i][y].getShip() != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x + i][y].setShip(ship);
			break;
		case SOUTH:
			if (y + ship.getLength() - 1 >= size)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if(ships[x][y + i].getShip() != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x][y + i].setShip(ship);
			break;
		case WEST:
			if (x - ship.getLength() + 1 < 0)
				throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				if(ships[x - i][y].getShip() != null)
					throw new BoardException();
			for (int i = 0; i < ship.getLength(); i++)
				ships[x - i][y].setShip(ship);
			break;
		}
	}

	@Override
	public boolean hasShip(int x, int y) {
		return ships[x][y].getShip() != null;
	}

	@Override
	public void setHit(boolean hit, int x, int y) {
		ships[x][y].addStrike();
		if(ships[x][y].isStruck())
			hits[x][y] = true;
	}

	@Override
	public Boolean getHit(int x, int y) {
		return hits[x][y];
	}

	@Override
	public Hit sendHit(int x, int y) {
		setHit(true, x, y);
		AbstractShip ship = ships[x][y].getShip();
		if(ship == null)
			return Hit.MISS;
		if(!ship.isSunk())
			return Hit.STIKE;
		if(ship instanceof Destroyer)
			return Hit.DESTROYER;
		if(ship instanceof Submarine)
			return Hit.SUBMARINE;
		if(ship instanceof BattleShip)
			return Hit.BATTLESHIP;
		if(ship instanceof Carrier)
			return Hit.CARRIER;
		return null;
	}

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
		if(hit == Hit.DESTROYER)
			System.out.println("Le " + ships[0].getName() + " est coulé.");
		board.sendHit(5, 5);
		board.print();
	}
	
	public static void main(String[] args) {
		testBoard();
	}
}