package view;

public class Main {

	private int height;
	private int length;

	public Main() {

		this.height = 20;
		this.length = 90;
	}

	// THIS IS TEMP, CAN BE REDSGNED TO MAKE METHOD SMALLER***
	private String welcomeScreen() {

		String welcomeStr = ("*" + "-".repeat(90) + "*\n");

		for (int i = 0; i < this.height; i++) {

			if (i == 0) {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(70));
				welcomeStr += ("Additional Info (>) ");
				welcomeStr += ("|\n");

			} else if (i == 5) {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(41));
				welcomeStr += ("Welcome");
				welcomeStr += (" ".repeat(42));
				welcomeStr += ("|\n");
			} else if (i == 6) {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(38));
				welcomeStr += ("To Your Music!");
				welcomeStr += (" ".repeat(38));
				welcomeStr += ("|\n");
			} else if (i == 12) {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(17));
				welcomeStr += ("Instructions");

				welcomeStr += (" ".repeat(32));

				welcomeStr += ("Main Menu");
				welcomeStr += (" ".repeat(20));
				welcomeStr += ("|\n");
			} else if (i == this.height - 1) {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(17));

				welcomeStr += ("Type either Instructions or Main Menu into the console.");

				welcomeStr += (" ".repeat(18));
				welcomeStr += ("|\n");
			} else {
				welcomeStr += ("|");
				welcomeStr += (" ".repeat(this.length));
				welcomeStr += ("|\n");
			}

		}
		welcomeStr += ("*" + "-".repeat(this.length) + "*\n");

		return welcomeStr;

	}

	// THIS IS TEMP, CAN BE REDSGNED TO MAKE METHOD SMALLER***
	private String instructionsScreen() {

		String instrStr = ("*" + "-".repeat(this.length) + "*\n");

		for (int i = 0; i < this.height; i++) {

			if (i == 0) {
				instrStr += ("| ");
				instrStr += ("(<) Back");
				instrStr += (" ".repeat(81));
				instrStr += ("|\n");

			} else if (i == 3) {

				instrStr += ("|");
				instrStr += (" ".repeat(21));
				instrStr += ("Instruction Manual for the Text-Based Console UI");
				instrStr += (" ".repeat(21));
				instrStr += ("|\n");

			} else if (i == 8) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "This text-based console UI allows users to navigate through menus and make selections by";
				instrStr += (" ".repeat(1));
				instrStr += ("|\n");
			} else if (i == 9) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "typing simple text commands. When a new menu appears, options are displayed in ASCII";
				instrStr += (" ".repeat(5));
				instrStr += ("|\n");
			} else if (i == 10) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "format with labels or numbers (e.g., \"Option 1,\" \"Option 2\"). To make a selection, simply";
				instrStr += (" ".repeat(0));
				instrStr += ("|\n");
			} else if (i == 11) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "type the corresponding number or label, and the input is not case-sensitive, meaning you";
				instrStr += (" ".repeat(1));
				instrStr += ("|\n");
			} else if (i == 12) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "can use uppercase, lowercase, or a mix of both. If you wish to go back to a previous";
				instrStr += (" ".repeat(5));
				instrStr += ("|\n");
			} else if (i == 13) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "screen, typing \"back\" will return you to the previous menu. There are no confirmation";
				instrStr += (" ".repeat(4));
				instrStr += ("|\n");
			} else if (i == 14) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "prompts in the program, so actions are performed immediately upon selection to save time";
				instrStr += (" ".repeat(1));
				instrStr += ("|\n");
			} else if (i == 15) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "and avoid unnecessary delays. This allows for quick and easy navigation through the";
				instrStr += (" ".repeat(6));
				instrStr += ("|\n");
			} else if (i == 16) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "options. Simply type the option as shown on the screen and press Enter to proceed. If";
				instrStr += (" ".repeat(4));
				instrStr += ("|\n");
			} else if (i == 17) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "you're unsure, most menus include an \"Exit\" or \"Back\" option to safely exit or return to";
				instrStr += (" ".repeat(1));
				instrStr += ("|\n");
			} else if (i == 18) {
				instrStr += ("|");
				instrStr += (" ".repeat(1));
				instrStr += "earlier screens.";
				instrStr += (" ".repeat(73));
				instrStr += ("|\n");
			} else {
				instrStr += ("|");
				instrStr += (" ".repeat(this.length));
				instrStr += ("|\n");
			}
		}

		instrStr += ("*" + "-".repeat(this.length) + "*\n");

		return instrStr;

	}

	private String additionalInfoScreen() {

		String infoStr = ("*" + "-".repeat(this.length) + "*\n");

		// AUTHORS, PROJECT NAME, CLASS NAME/LEVEL, PRUPOSE OF PROJECT
		for (int i = 0; i < this.height; i++) {
			if (i == 0) {
				infoStr += ("| ");
				infoStr += ("(<) Back");
				infoStr += (" ".repeat(81));
				infoStr += ("|\n");

			} else {
				infoStr += ("|");
				infoStr += (" ".repeat(this.length));
				infoStr += ("|\n");
			}

		}

		infoStr += ("*" + "-".repeat(this.length) + "*\n");

		return infoStr;
	}

	public static void main(String[] args) {

		Main m = new Main();

		System.out.println(m.welcomeScreen());
		System.out.println(m.instructionsScreen());
		System.out.println(m.additionalInfoScreen());

	}
}
