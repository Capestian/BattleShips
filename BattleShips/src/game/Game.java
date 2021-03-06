package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ship.*;

public class Game {
	public static int BOARD_SIZE = 12;
	public static int MAX_SHIPS = 8;
	public static int MIN_SHIPS = 4;
	
	public static void main(String[] args) {
//		playerVSAI();
		AIAlone();
	}
	
	public static void AIAlone() {
		Board board = new Board("Bataille navale", BOARD_SIZE);
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
				s.append(" coul?");
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
		Board playerBoard = new Board(name, BOARD_SIZE);
		Board aiBoard = new Board("AI", BOARD_SIZE);
		List<AbstractShip> playerShips = Arrays.asList(generateShips());
		List<AbstractShip> aiShips = Arrays.asList(generateShips());
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
				s.append(" coul?");
			}
			System.out.println(s);
			s.setLength(0);
			aiBoard.print();
			
			// ai hit
			hit = ai.sendHit(coords);
			s.append("" + coords[0] + " " + coords[1] + " : " + hit.getLabel());
			if (hit.getValue() > 0) {
				player.loseAShip();
				s.append(" coul?");
			}
			System.out.println(s);
			s.setLength(0);
			playerBoard.print();
		} while (!player.hasLost() && !ai.hasLost());
		aiBoard.print();
		playerBoard.print();
		if(player.hasLost() && ai.hasLost())
			System.out.println("Egalit? !");
		if(player.hasLost())
			System.out.println("Victoire de l'IA");
		if(ai.hasLost())
			System.out.println("Victoire de " + name + ", f?licitation");
	}
	
	/**
	 * Generate a array of random ships. The lenght is between MIN_SHIPS and MAX_SHIPS.
	 * 
	 * @return the array of ships
	 */
	public static AbstractShip[] generateShips() {
		Random rnd = new Random();
		int nb = rnd.nextInt(MAX_SHIPS - MIN_SHIPS + 1);
		AbstractShip[] ships = new AbstractShip[nb + MIN_SHIPS - 1];
		for(int i = 0; i < ships.length; i++) {
			switch (rnd.nextInt(4)) {
			case 1:
				ships[i] = new Submarine("Sous-marin", 's');
				break;
			case 2:
				ships[i] = new BattleShip("Croiseur", 'c');
				break;
			case 3:
				ships[i] = new Carrier("Porte-avion", 'p');
				break;
			default:
				ships[i] = new Destroyer("Fr?gate", 'd');
				break;
			}
		}
		return ships;
	}
}
