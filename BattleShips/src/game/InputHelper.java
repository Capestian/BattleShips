package game;

import java.util.Scanner;

import ship.Orientation;

public final class InputHelper {

	private InputHelper() {
	}

	public static class ShipInput {
		public Orientation orientation;
		public int x;
		public int y;
	}

	public static class CoordInput {
		public int x;
		public int y;
	}

	public static ShipInput readShipInput() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		ShipInput res = new ShipInput();
		boolean done = false;
		do {
			try {
				String[] in = sin.nextLine().toLowerCase().split(" ");
				if (in.length == 2 && in[1].length() == 1) {
					String coord = in[0];
					res.orientation = Orientation.fromCharacter(in[1].charAt(0));
					if (res.orientation != null) {
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
						done = true;
					}
				}
			} catch (Exception e) {
				// nop
			}

			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0 n'");
			}

		} while (!done && sin.hasNextLine());

		return res;
	}

	public static CoordInput readCoordInput() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		CoordInput res = new CoordInput();
		boolean done = false;
		do {
			try {
				String coord = sin.nextLine().toLowerCase();
				res.x = coord.charAt(0) - 'a';
				res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
				done = true;
			} catch (Exception e) {
				// nop
			}

			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0'");
			}

		} while (!done && sin.hasNextLine());

		return res;
	}

}
