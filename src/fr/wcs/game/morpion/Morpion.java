package fr.wcs.game.morpion;

import java.util.Random;

public class Morpion implements Runnable {
	
	public static String REGEX_IS_COL_OR_LINE = "[1-3]";
	public static String PLAYER_SYMBOL = "\u25CF";
	public static String COMPUTER_SYMBOL = "\u2717";

	public static void main(String[] args) {
		new Morpion().run();
	}

	private GameIhm ihm;
	private String[][] grid;
	private Random rand;

	public Morpion() {
		this.ihm = new GameIhm();
		this.grid = new String[][] {
			{ " ", " ", " "},
			{ " ", " ", " "},
			{ " ", " ", " "}
		};
		this.rand = new Random();
	}

	@Override
	public void run() {
		boolean exit  = false;
		while (!exit) {
			this.ihm.display("Bienvenue dans le jeu du morpion !");
			this.play();
			String choice = this.ihm.readInput("Voulez-vous continuer ? (O/n) : ");
			if (choice.matches("[nN]")) {
				exit = true;
			} else {
				// Réinitialiser les données du jeu pour recommencer une partie.
				this.grid = new String[][] {
					{ " ", " ", " "},
					{ " ", " ", " "},
					{ " ", " ", " "}
				};
			}
		}
	}
	
	public void play() {
		this.ihm.display("Nouvelle partie !");
		boolean won = false;
		while (!won) {
			this.ihm.displayGrid(this.grid);
			if (this.playerTurn()) {
				if (this.checkAllWinCases()) {
					won = true;
					this.ihm.display("Bravo vous avez gagné la partie !");
				} else {
					this.computerTurn();
					if (this.checkAllWinCases()) {
						won = true;
						this.ihm.display("Dommage, l'ordinateur a gagné la partie !");
					}
				}
			}
			
		}
	}
	
	private boolean playerTurn() {
		boolean hasPlayed = false;
		this.ihm.display("A vous de jouer !");
		String strCol = this.ihm.readInput("Saisir un numéro de colonne entre 1 et 3 : ");
		String strLine = this.ihm.readInput("Saisir un numéro de ligne entre 1 et 3 : ");
		if (strCol.matches(REGEX_IS_COL_OR_LINE) && strLine.matches(REGEX_IS_COL_OR_LINE)) {
			int col = Integer.parseInt(strCol);
			int line = Integer.parseInt(strLine);
			if (this.grid[line - 1][col - 1].isBlank()) {
				this.grid[line - 1][col - 1] = PLAYER_SYMBOL;
				hasPlayed = true;
			} else {
				this.ihm.display("Impossible de jouer ici, la case a déjà été jouée !");
			}
		} else {
			this.ihm.display("Les coordonnées saisies ne sont pas correctes, veuillez recommencer.");
		}
		return hasPlayed;
	}
	
	private void computerTurn() {
		boolean test = true;
		while (test) {
			int randCell = this.rand.nextInt(8);
			int col = randCell / 3;
			int line = randCell - col * 3;
			if (this.grid[line][col].isBlank()) {
				test = false;
				this.grid[line][col] = COMPUTER_SYMBOL;
			}
		}
	}
	
	private boolean checkAllWinCases() {
		boolean result = false;
		if (this.checkWinCase(this.grid[0][0], this.grid[0][1], this.grid[0][2])
				|| this.checkWinCase(this.grid[1][0], this.grid[1][1], this.grid[1][2])
				|| this.checkWinCase(this.grid[2][0], this.grid[2][1], this.grid[2][2])
				|| this.checkWinCase(this.grid[0][0], this.grid[1][0], this.grid[2][0])
				|| this.checkWinCase(this.grid[1][0], this.grid[1][1], this.grid[2][1])
				|| this.checkWinCase(this.grid[2][0], this.grid[2][1], this.grid[2][2])
				|| this.checkWinCase(this.grid[0][2], this.grid[1][1], this.grid[2][0])
				|| this.checkWinCase(this.grid[0][0], this.grid[1][1], this.grid[2][2])) {
			result = true;
		}
		return result;
	}
	
	private boolean checkWinCase(String cell1, String cell2, String cell3) {
		return !cell1.isBlank() && cell1.equals(cell2) && cell1.equals(cell3);
	}
}
