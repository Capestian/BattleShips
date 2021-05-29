package game;

import ship.AbstractShip;
import ship.BattleShip;
import ship.Carrier;
import ship.Destroyer;
import ship.Orientation;
import ship.Submarine;

public class Board implements IBoard {
	// Constants:
	//private static String retourLigne = System.getProperty("line.separator");
	public static int defaultSize = 10;
	public static int maximunSize = 26;
	public static Character seaChar = '.';
	public static Character hitChar = 'x';
	
	// Attributes:
	@SuppressWarnings("unused")
	private String name;
	private int size;
	private Character[][] ships;
	private Boolean[][] hits;
	
	public Board(String name, int size) {
		this.name = name;
		if(size > maximunSize)
			this.size = maximunSize;
		else
			this.size = size;
		this.ships = new Character[this.size][this.size];
		this.hits = new Boolean[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
			for(int k = 0; k < this.size; k++) {
				ships[i][k] = seaChar;
				hits[i][k] = false;
			}
		}
	}
	
	public Board(String name) {
		this(name, defaultSize);
	}
	
	public void print() {
		StringBuffer s = new StringBuffer();
		s.append("Navires :");
		if(size > 3) {
			for (int i = 3; i < size; i++) {
				s.append("  ");
			}
		}
		s.append("Frappes :\n ");
		if(size>=10)
			s.append(" ");
		for(char a = 'A'; a < 'A'+size; a++) {
			s.append(" " + a);
		}
		s.append("     ");
		if(size>=10)
			s.append(" ");
		for(char a = 'A'; a < 'A'+size; a++) {
			s.append(" " + a);
		}
		s.append("\n");
		for(int i = 0; i < size; i++) {
			if(i<9)
				s.append(" ");
			s.append(i+1);
			for(int k = 0; k < size; k++) {
				s.append(" " + ships[k][i]);
			}
			s.append("    ");
			if(i<9)
				s.append(" ");
			s.append(i+1);
			for(int k = 0; k < size; k++) {
				if(hits[k][i])
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
	public void putShip(AbstractShip ship, int x, int y) {
		if(x >= 0 && x < size && y >= 0 && y < size) {
			switch (ship.getOrientation()) {
			case NORTH :
				if(y - ship.getLength() + 1 >= 0)
					for(int i = 0; i < ship.getLength(); i++)
						ships[x][y-i] = ship.getLabel();
				break;
			case EAST :
				if(x + ship.getLength() - 1 < size)
					for(int i = 0; i < ship.getLength(); i++)
						ships[x+i][y] = ship.getLabel();
				break;
			case SOUTH :
				if(y + ship.getLength() - 1 < size)
					for(int i = 0; i < ship.getLength(); i++)
						ships[x][y+i] = ship.getLabel();
				break;
			case WEST :
				if(x - ship.getLength() + 1 >= 0)
					for(int i = 0; i < ship.getLength(); i++)
						ships[x-i][y] = ship.getLabel();
				break;
			}
		}
	}

	@Override
	public boolean hasShip(int x, int y) {
		return ships[x][y] != seaChar;
	}

	@Override
	public void setHit(boolean hit, int x, int y) {
		hits[x][y] = true;
	}

	@Override
	public Boolean getHit(int x, int y) {
		return hits[x][y];
	}
	
	public static void main(String[] args) {
		Board board = new Board("Bataille navale", 12);
		Destroyer d = new Destroyer("Destroyer", 'd', Orientation.WEST);
		board.putShip(d, 0, 0);
		board.putShip(d, 1, 11);
		
		Submarine s = new Submarine("Submarine", 's', Orientation.NORTH);
		board.putShip(s, 4, 1);
		board.putShip(s, 6, 2);
		board.putShip(s, 8, 3);
		board.putShip(s, 1, 12);
		
		BattleShip b = new BattleShip("BattleShip", 'b', Orientation.EAST);
		board.putShip(b, 7, 3);
		board.putShip(b, 8, 5);
		board.putShip(b, 9, 7);
		board.putShip(b, 10, 9);
		
		Carrier c = new Carrier("Carrier", 'c', Orientation.SOUTH);
		board.putShip(c, 3, 6);
		board.putShip(c, 5, 7);
		board.putShip(c, 7, 8);
		board.putShip(c, 9, 9);
		
		board.print();
	}
}