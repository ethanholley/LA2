package view;

import model.*;
import java.util.Scanner;

public class LoginPage {
	private WriteUser writeUser;
	private Scanner scanner;

	public LoginPage() {
		writeUser = new WriteUser(); // Initialize WriteUser class to interact with backend
		scanner = new Scanner(System.in);
	}

	public void start() {
		System.out.println("Welcome to the Music Store!");
		System.out.println("Please choose an option:");
		System.out.println("1. Login");
		System.out.println("2. Create New Account");
		String choice = scanner.nextLine().toLowerCase();
		if (choice.equals("login")) {
			login();
		} else if (choice.equals("create new account")) {
			createAccount();
		} else {
			System.out.println("Invalid choice, please try again.");
			start(); // Recursively ask again
		}
	}

	private void login() {
		System.out.println("\n*LOGIN PAGE*:\n");
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		// Authenticate the user with WriteUser class
		Account user = writeUser.login(username, password);
		if (user != null) {
			System.out.println("Login successful! Welcome, " + user.getUsername() + "\n\n");
			// After successful login, proceed to main menu
			new Main();
		} else {
			System.out.println("Invalid username or password. Please try again or create an acccount.\n\n");
			start(); // Recursively ask again
		}
	}

	private void createAccount() {
		System.out.print("Enter a new username: ");
		String username = scanner.nextLine();
		System.out.print("Enter a new password: ");
		String password = scanner.nextLine();
		boolean newUser = writeUser.addUser(username, password);
		if (newUser) {
			System.out.println("Account created successfully!\n\n");
			login(); // Once account is created, prompt user to login
		} else {
			System.out.println("Username already exists. Please try again.\n");
			start(); // Recursively ask again
		}
	}
}
