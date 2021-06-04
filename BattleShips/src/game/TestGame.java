package game;

import java.util.ArrayList;
import java.util.List;

import ship.*;

public class TestGame {
	public static void main(String[] args) {
		Board board = new Board("Bataille navale", 12);
		BattleShipsAI ai = new BattleShipsAI(board, board);
		List<AbstractShip> ships = new ArrayList<>();
		ships.add(new Destroyer("Destroyer", 'd'));
		ships.add(new Submarine("Submarine A", 's'));
		ships.add(new Submarine("Submarine B", 's'));
		ships.add(new BattleShip("BattleShip", 'b'));
		ships.add(new Carrier("Carrier", 'c'));
		ai.putShips(ships);
		int wreckCounter = 0;
		Hit hit;
		int[] coords = new int[2];
		StringBuffer s = new StringBuffer();
		while (wreckCounter < ships.size()) {
			hit = ai.sendHit(coords);
			s.append("" + coords[0] + " " + coords[1] + " : " + hit.getLabel());
			if (hit.getValue() > 0) {
				wreckCounter++;
				s.append(" coulé");
			}
			System.out.println(s);
			s.setLength(0);
			board.print();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
