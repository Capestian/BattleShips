package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ship.*;

public class Game {
	public static void main(String[] args) {
		playerVSAI();
	}
	
	public static void AIAlone() {
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
	
	public static void playerVSAI() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		System.out.println("Entrez le nom du joueur");
		String name = sin.nextLine();
		Board playerBoard = new Board(name, 12);
		Board aiBoard = new Board("AI", 12);
		List<AbstractShip> playerShips = new ArrayList<>();
//		playerShips.add(new Destroyer("Destroyer", 'd'));
//		playerShips.add(new Submarine("Submarine A", 's'));
//		playerShips.add(new Submarine("Submarine B", 's'));
//		playerShips.add(new BattleShip("BattleShip", 'b'));
		playerShips.add(new Carrier("Carrier", 'c'));
		List<AbstractShip> aiShips = new ArrayList<>();
		aiShips.add(new Destroyer("Destroyer", 'd'));
		aiShips.add(new Submarine("Submarine A", 's'));
		aiShips.add(new Submarine("Submarine B", 's'));
		aiShips.add(new BattleShip("BattleShip", 'b'));
		aiShips.add(new Carrier("Carrier", 'c'));
		Player player = new Player(playerBoard, aiBoard, playerShips);
		AIPlayer ai = new AIPlayer(aiBoard, playerBoard, aiShips);
		aiBoard.print();
		playerBoard.print();
		player.putShips();
		ai.putShips();
		
		Hit hit;
		int[] coords = new int[2];
		StringBuffer s = new StringBuffer();
		do {
			// player hit
			aiBoard.print();
			hit = player.sendHit(coords);
			s.append("" + coords[0] + " " + coords[1] + " : " + hit.getLabel());
			if (hit.getValue() > 0) {
				ai.loseAShip();
				s.append(" coulé");
			}
			System.out.println(s);
			s.setLength(0);
			aiBoard.print();
			
			// ai hit
			hit = ai.sendHit(coords);
			s.append("" + coords[0] + " " + coords[1] + " : " + hit.getLabel());
			if (hit.getValue() > 0) {
				player.loseAShip();
				s.append(" coulé");
			}
			System.out.println(s);
			s.setLength(0);
			playerBoard.print();
		} while (!player.hasLost() && !ai.hasLost());
		aiBoard.print();
		playerBoard.print();
		if(player.hasLost() && ai.hasLost())
			System.out.println("Egalité !");
		if(player.hasLost())
			System.out.println("Victoire de l'IA");
		if(ai.hasLost())
			System.out.println("Victoire de " + name + ", félicitation");
	}
}
