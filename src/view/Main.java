package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Main {

	private MusicStore ms;
	private LibraryModel lib;

	public Main() {

		ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
		ms = pf.getMusicStore();
		lib = new LibraryModel();
		mainMenu();

	}

	private void mainMenu() {
		System.out.println("Welcome to Your Music Aplication.\n");
		System.out.println("Choose one of the options below in the Console\n");
		System.out.println("Search Music Store     See Library     Search Library     Create Playlist\n");
		Scanner scanner = new Scanner(System.in);

		String execution = scanner.nextLine().toLowerCase();
		pickDestination(execution);
	}

	private void pickDestination(String execution) {

		if (execution.equals("search music store")) {
			System.out.println("search music store.");

		} else if (execution.equals("see library")) {
			System.out.println("see library.");

		} else if (execution.equals("search library")) {
			searchLibrary();

		} else if (execution.equals("create playlist")) {
			System.out.println("create playlist.");
		} else {
			System.out.println("Invalid Input, please try again.");
			Scanner scanner = new Scanner(System.in);
			execution = scanner.nextLine().toLowerCase();
			pickDestination(execution);
		}

	}

	private void searchLibrary() {
		System.out.println(
				"Welcome to the library Search. Here is a list of the things you can search in the library:\n\n");
		System.out.println("Please pick one options to search by.\n");
		System.out.println("Song   Artist   Album\n");

		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();

		boolean flag = true;

		while (flag) {
			if (execution.equals("song")) {
				librarySongSearch();
				flag = false;
			} else if (execution.equals("artist")) {

				flag = false;
			} else if (execution.equals("album")) {

				flag = false;
			} else {
				System.out.println("Invalid Input, please try again.");
				Scanner newScanner = new Scanner(System.in);
				execution = newScanner.nextLine().toLowerCase();
			}
		}

	}

	// need to test this with ms search
	private void librarySongSearch() {
		System.out.println("What Song are you Searching for?\n\n");
		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();
		Song song = null;

		ArrayList<Song> songList = lib.searchSongbyTitle(execution);

		songList.add(new Song("DNA.", "Kendrick Lamar", "Damn."));
//		songList.add(new Song("DNA.", "Bendrick Yamar", "Damn."));

		if (songList.size() == 0) {
			System.out.println("I'm sorry, I couldn't find that song. Please try again.");
			librarySongSearch();
		} else if (songList.size() > 1) {
			System.out
					.println("There are more than 1 song with ths title.\nWhich Artist of the Song would you like?\n");
			int i = 0;
			while (i < songList.size()) {
				System.out.println(songList.get(i).getTitle() + " By: " + songList.get(i).getArtist());
				i++;
			}
			System.out.println("\nPick the Artist of the Song you want to choose from.\n");
			boolean flag = true;

			while (flag) {
				String artistChoice = scanner.nextLine().toLowerCase();
				for (Song curSong : songList) {
					if (curSong.getArtist().toLowerCase().equals(artistChoice)) {
						song = new Song(curSong);
						flag = false;
						break;
					}
				}
				if (flag) {
					System.out.println("Please Try Again.\n");
				}
			}
		} else {
			song = new Song(songList.get(0));
		}
		librarySearchPlaylist(song);
	}

	private void librarySearchPlaylist(Song song) {
		System.out.println("What Would you like to do with this? Here are your options.\n");
		System.out.println("Add to Playlist\n");

		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();

		if (execution.equals("add to playlist")) {

			System.out.println("What is the name of the Playlist?\n");
			String playlistName = scanner.nextLine().toLowerCase();
			ArrayList<Playlist> playlistList = lib.getAllPlayList();

			boolean found = false;
			for (Playlist pl : playlistList) {
				if (pl.getPlaylistName().toLowerCase().equals(playlistName)) {
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Playlist not created. Create Playlist at main menu.\n\n");
			} else {
				lib.addSongToPlaylist(playlistName, song.getTitle(), song.getArtist(), song.getAlbum());
			}

		} else {
			System.out.println("Im Sorry, Invalid Input, Please try again.\n\n");
			librarySearchPlaylist(song);
		}
		System.out.println("loading back to Main Menu...\\n\\n");
		mainMenu();
	}

	public static void main(String[] args) {

		Main m = new Main();

	}
}
