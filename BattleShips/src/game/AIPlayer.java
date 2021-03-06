package game;

import java.util.Arrays;
import java.util.List;

import ship.AbstractShip;

public class AIPlayer extends Player {
	private BattleShipsAI ai;

	public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
		super(ownBoard, opponentBoard, ships);
		ai = new BattleShipsAI(ownBoard, opponentBoard);
	}
	
	@Override
	public void putShips() {
		ai.putShips(Arrays.asList(this.ships));
	}
	
	@Override
	public Hit sendHit(int[] coords) {
		return ai.sendHit(coords);
	}
}
