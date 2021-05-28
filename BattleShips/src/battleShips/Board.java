package battleShips;

import java.io.IOException;

public class Board {
	// Constants:
	//private static String retourLigne = System.getProperty("line.separator");
	public static int defaultSize = 10;
	public static int maximunSize = 26;
	
	// Attributes:
	private String name;
	private int size;
	private char[][] ships;
	private boolean[][] hits;
	
	public Board(String name, int size) {
		this.name = name;
		if(size > maximunSize)
			this.size = maximunSize;
		else
			this.size = size;
		this.ships = new char[size][size];
		this.hits = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				ships[i][k] = '.';
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
				s.append(" " + ships[i][k]);
			}
			s.append("    ");
			if(i<9)
				s.append(" ");
			s.append(i+1);
			for(int k = 0; k < size; k++) {
				if(hits[i][k])
					s.append(" x");
				else
					s.append(" .");
			}
			s.append("\n");
		}
		System.out.println(s.toString());
		try {
			Runtime.getRuntime().exec("clear");
			s.toString();
		} 
		catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		Board board = new Board("Bataille navale", 12);
		board.print();
	}
}