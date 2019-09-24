package fr.wcs.game.morpion;

import java.util.Scanner;

public class GameIhm {

	private final Scanner scanner;

	public GameIhm() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Méthode d'affichage d'informations à l'utilisateur.
	 * 
	 * @param message le message à afficher.
	 */
	public void display(String message, boolean inline) {
		if (inline) {
			System.out.print(message);
		} else {
			System.out.println(message);
		}
	}

	public void display(String message) {
		this.display(message, false);
	}

	public void displayGrid(String[][] grid) {
		this.display("Grille du jeu :");
		this.displayTopGridLine();
		for (int i = 0; i < grid.length; ++i) {
			String[] line = grid[i];
			this.display("\t\u2502 ", true);
			for (String cell : line) {
				this.display(cell + " \u2502 ", true);
			}
			// Retour à la ligne.
			this.display("");
			if (i != grid.length - 1) {
				this.displayMiddleGridLine();
			}
		}
		this.displayBottomGridLine();
	}

	public void displayMiddleGridLine() {
		this.display("\t\u251C\u2500", true);
		this.display("\u2500\u2500\u253C", true);
		this.display("\u2500\u2500\u2500\u253C", true);
		this.display("\u2500\u2500\u2500\u2524");
	}

	public void displayTopGridLine() {
		this.display("\t\u250C\u2500", true);
		this.display("\u2500\u2500\u252C", true);
		this.display("\u2500\u2500\u2500\u252C", true);
		this.display("\u2500\u2500\u2500\u2510");
	}

	public void displayBottomGridLine() {
		this.display("\t\u2514\u2500", true);
		this.display("\u2500\u2500\u2534", true);
		this.display("\u2500\u2500\u2500\u2534", true);
		this.display("\u2500\u2500\u2500\u2518");
	}

	/**
	 * Méthode de récupération d'une saisie utilisateur.
	 * 
	 * @param label le message a afficher avant de demander la saisie.
	 * @return String la saisie utilisateur.
	 */
	public String readInput(String label) {
		this.display(label);
		return this.scanner.nextLine();
	}
}
