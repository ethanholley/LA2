package model;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WriteUser {

	// Account -> 3 Instance variables:
	// - String user name
	// - string password
	// - ArrayList or HashMap? -> keep track of all data (within library)
	private static final String FILE_PATH = "/Users/chancekrueger/Desktop/AccountFolder/allAcounts.txt";
	private Map<String, Account> users;

	public WriteUser() {
		this.users = new HashMap<>();
	}

	// Hash Passwords using SHA-256
	private String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}

	// Add a new user
	public boolean addUser(String username, String password) {
		if (users.containsKey(username)) {
			return false; // Username already exists
		}
		Account newUser = new Account(username, hashPassword(password));
		users.put(username, newUser);
		saveUsersToFile(); // Save updated user list to file
		return true;
	}

	// Authenticate User (Login)
	public Account login(String username, String password) {
		if (users.containsKey(username)) {
			Account user = users.get(username);
			if (user.getPassword().equals(hashPassword(password))) {
				return user; // Successful login
			}
		}
		return null; // Invalid username or password
	}

	// Save Users to a Text File
	private void saveUsersToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Account user : users.values()) {
				writer.write(user.getUsername() + "," + user.getPassword());
				for (Object data : user.getDataList()) {
					writer.write("," + data.toString());
				}
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Load Users from the Text File
	private void loadUsersFromFile() {
		File file = new File(FILE_PATH);
		if (!file.exists())
			return; // No file yet, skip loading

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length < 2)
					continue; // Skip invalid lines

				String username = parts[0];
				String passwordHash = parts[1];
				Account user = new Account(username, passwordHash);

				// Load user’s saved data (if any)
				for (int i = 2; i < parts.length; i++) {
					user.getDataList().add(parts[i]);
				}

				users.put(username, user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Logout and save user data
	public void logout() {
		saveUsersToFile();
	}

	// Get all users (for testing)
	public Set<String> getAllUsernames() {
		return users.keySet();
	}

	public static void main(String[] args) {

		WriteUser wf = new WriteUser();

		wf.loadUsersFromFile();
		System.out.println(wf.users.toString());
		System.out.println();

//		wf.addUser("chance", "pw");
//		wf.addUser("chance2", "pw1");

		System.out.println(wf.login("ethan", "pw"));
		System.out.println(wf.login("chance", "pw"));
		System.out.println(wf.login("chance", "pw2"));

		System.out.println("HELLOWORLD");
	}

}
