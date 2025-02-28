
package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Main {

	private MusicStore ms;
	private LibraryModel lib;

	public Main() {

		ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");
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
			searchMusicStore();

		} else if (execution.equals("see library")) {
			seeWholeLibrary();

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

	private void searchMusicStore() {
		System.out.println(
				"Welcome to the Music Store. Here you can add songs and albums to your library or playlists\n");
		System.out.println("Please pick one option to search in the Music Store: \n");
		System.out.println(
				"Search Song By Artist    Search Song By Title    Search Album by Artist    Search Album by Title");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine().toLowerCase();
		boolean checkResponse = checkMusicStoreInput(userInput);
		while (!checkResponse) {
			System.out.println("Invalid Input, please try again.");
			scanner = new Scanner(System.in);
			userInput = scanner.nextLine().toLowerCase();
			checkResponse = checkMusicStoreInput(userInput);
		}

		if (userInput.equals("search song by artist")) {
			System.out.println("Enter an artist to see all their songs in the store:");
			Scanner scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();
			ArrayList<Song> artistSongList = ms.searchSongbyArtist(userInput);

			while (artistSongList.size() == 0) {
				System.out.println(
						"Sorry, this artist does not have any songs in the music store. Try searching for a different artist.");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				artistSongList = ms.searchSongbyArtist(userInput);
			}
			for (Song song : artistSongList) {
				System.out.println(song.toString());
			}
			System.out.println();
			System.out.println("Choose one of these songs from this artist.");
			System.out.println("You can add the song to your library, mark as a favorite, or add it to a playlist.");
			System.out.println("Enter the title of the song you want to add: ");
			String songChoice = scanner.nextLine().toLowerCase();
			Song song = null;
			for (Song curSong : artistSongList) {
				if (curSong.getTitle().toLowerCase().equals(songChoice)) {
					song = new Song(curSong);
					break;
				}
			}
			if (song == null) {
				System.out.println("Sorry, song not in Artist's list of songs, going back to main menu.");
				mainMenu();
			}

			System.out.println("Here are your options for the song: " + song.getTitle());
			System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist");
			scanner2 = new Scanner(System.in);
			userInput = scanner.nextLine().toLowerCase();

			if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
					&& !userInput.equals("add it to playlist")) {
				System.out.println("Sorry, this is not one of the menu options, returning to main menu.");
				mainMenu();
			}

			if (userInput.equals("add song to library")) {
				System.out.println("Great! Adding " + song.getTitle() + " to your music library.");
				System.out.println("Going back to main menu. Go to see library to see changes.");
				lib.addSongToLibrary(ms, song.getTitle().toLowerCase(), song.getArtist().toLowerCase());
				mainMenu();
			} else if (userInput.equals("mark song as favorite")) {
				System.out.println("Great! Adding " + song.getTitle() + " as a favorite.");
				System.out.println(
						"Going back to main menu. Go to favorite songs in see library menu option to see changes.");
				ms.setFavoriteOfSong(song.getArtist().toLowerCase(), song.getTitle().toLowerCase());
				lib.addSongToLibrary(ms, song.getTitle().toLowerCase(), song.getArtist().toLowerCase());
				mainMenu();
			} else if (userInput.equals("add it to playlist")) {
				ArrayList<Playlist> allPlaylists = lib.getAllPlayList();
				if (allPlaylists.size() == 0) {
					System.out.println("Sorry, you have no playlists created. You can create one in the main menu");
					mainMenu();
				} else {
					System.out.println("You're playlists: ");
					for (Playlist playlist : allPlaylists) {
						System.out.println(playlist.getPlaylistName());
					}
					System.out.println();
					System.out.println("Enter which playlist name you want to add the song to: ");
					scanner2 = new Scanner(System.in);
					String input = scanner.nextLine().toLowerCase();
					boolean found = false;
					for (Playlist playlist : allPlaylists) {
						if (playlist.getPlaylistName().toLowerCase().equals(input)) {
							found = true;
							System.out.println(
									"Great! Adding " + song.getTitle() + " to " + playlist.getPlaylistName() + "!");
							System.out.println("Going back to main menu");
							playlist.addSongToPlaylist(song.getTitle(), song.getArtist(), song.getAlbum());
							mainMenu();
							break;
						}
					}
					if (!found) {
						System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
					}
					mainMenu();
				}
			}

		} else if (userInput.equals("search song by title")) {
			System.out.println("Enter a song title to see if it is in the store:");
			Scanner scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();
			ArrayList<Song> songList = ms.searchSongbyTitle(userInput);
			if (songList.size() == 0) {
				System.out.println("Sorry, that song is not in the store. Returning to main menu");
				mainMenu();
			} else if (songList.size() == 1) {
				System.out.println("Here are your options for the song: " + songList.get(0).getTitle());
				System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist");
				scanner2 = new Scanner(System.in);
				userInput = scanner.nextLine().toLowerCase();
				if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
						&& !userInput.equals("add it to playlist")) {
					System.out.println("Sorry, this is not one of the menu options, returning to main menu.");
					mainMenu();
				}

				if (userInput.equals("add song to library")) {
					System.out.println("Great! Adding " + songList.get(0).getTitle() + " to your music library.");
					System.out.println("Going back to main menu. Go to see library to see changes.");
					lib.addSongToLibrary(ms, songList.get(0).getTitle().toLowerCase(), songList.get(0).getArtist().toLowerCase());
					mainMenu();
				} else if (userInput.equals("mark song as favorite")) {
					System.out.println("Great! Adding " + songList.get(0).getTitle() + " as a favorite.");
					System.out.println(
							"Going back to main menu. Go to favorite songs in see library menu option to see changes.");
					ms.setFavoriteOfSong(songList.get(0).getArtist().toLowerCase(), songList.get(0).getTitle().toLowerCase());
					lib.addSongToLibrary(ms, songList.get(0).getTitle().toLowerCase(), songList.get(0).getArtist().toLowerCase());
					mainMenu();
				} else if (userInput.equals("add it to playlist")) {
					ArrayList<Playlist> allPlaylists = lib.getAllPlayList();
					if (allPlaylists.size() == 0) {
						System.out.println("Sorry, you have no playlists created. You can create one in the main menu");
						mainMenu();
					} else {
						System.out.println("You're playlists: ");
						for (Playlist playlist : allPlaylists) {
							System.out.println(playlist.getPlaylistName());
						}
						System.out.println();
						System.out.println("Enter which playlist name you want to add the song to: ");
						scanner2 = new Scanner(System.in);
						String input = scanner.nextLine().toLowerCase();
						boolean found = false;
						for (Playlist playlist : allPlaylists) {
							if (playlist.getPlaylistName().toLowerCase().equals(input)) {
								found = true;
								System.out.println("Great! Adding " + songList.get(0).getTitle() + " to "
										+ playlist.getPlaylistName() + "!");
								System.out.println("Going back to main menu");
								playlist.addSongToPlaylist(songList.get(0).getTitle(), songList.get(0).getArtist(),
										songList.get(0).getAlbum());
								mainMenu();
								break;
							}
						}
						if (!found) {
							System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
						}
						mainMenu();
					}
				}
			} else if (songList.size() == 2) {
				System.out
						.println("Multiple results with that title. Which one do you want to pick. Enter the artist. ");
				for (Song song : songList) {
					System.out.println(song.toString());
				}
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				Song chosenSong = null;
				for (Song song : songList) {
					if (song.getArtist().toLowerCase().equals(userInput)) {
						chosenSong = new Song(song);
					}
				}
				if (chosenSong == null) {
					System.out.println("Sorry, not one of the artists available, going back to main menu");
					mainMenu();
				}

				System.out.println("Here are your options for the song: " + chosenSong.getTitle());
				System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist");
				scanner2 = new Scanner(System.in);
				userInput = scanner.nextLine().toLowerCase();
				System.out.println(userInput);
				if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
						&& !userInput.equals("add it to playlist")) {
					System.out.println("Sorry, this is not one of the menu options, returning to main menu.");
					mainMenu();
				}

				if (userInput.equals("add song to library")) {
					System.out.println("Great! Adding " + chosenSong.getTitle() + " to your music library.");
					System.out.println("Going back to main menu. Go to see library to see changes.");
					lib.addSongToLibrary(ms, chosenSong.getTitle(), chosenSong.getArtist().toLowerCase());
					mainMenu();

				} else if (userInput.equals("mark song as favorite")) {
					System.out.println("Great! Adding " + chosenSong.getTitle() + " as a favorite.");
					System.out.println(
							"Going back to main menu. Go to favorite songs in see library menu option to see changes.");
					ms.setFavoriteOfSong(chosenSong.getArtist().toLowerCase(), chosenSong.getTitle().toLowerCase());
					lib.addSongToLibrary(ms, chosenSong.getTitle().toLowerCase(), chosenSong.getArtist().toLowerCase());
					mainMenu();

				} else if (userInput.equals("add it to playlist")) {
					ArrayList<Playlist> allPlaylists = lib.getAllPlayList();
					if (allPlaylists.size() == 0) {
						System.out.println("Sorry, you have no playlists created. You can create one in the main menu");
						mainMenu();
					} else {
						System.out.println("You're playlists: ");
						for (Playlist playlist : allPlaylists) {
							System.out.println(playlist.getPlaylistName());
						}
						System.out.println();
						System.out.println("Enter which playlist name you want to add the song to: ");
						scanner2 = new Scanner(System.in);
						String input = scanner.nextLine().toLowerCase();
						boolean found = false;
						for (Playlist playlist : allPlaylists) {
							if (playlist.getPlaylistName().toLowerCase().equals(input)) {
								found = true;
								System.out.println("Great! Adding " + chosenSong.getTitle() + " to "
										+ playlist.getPlaylistName() + "!");
								System.out.println("Going back to main menu");
								playlist.addSongToPlaylist(chosenSong.getTitle(), chosenSong.getArtist(),
										chosenSong.getAlbum());
								mainMenu();
								break;
							}
						}
						if (!found) {
							System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
						}
						mainMenu();
					}

				}
			}
		} else if(userInput.equals("search album by artist")) {
			System.out.println("Enter an artist to see all their albums in the store:");
			Scanner scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();
			ArrayList<Album> albumArtistList = ms.searchAlbumArtist(userInput);
			if (albumArtistList.size() == 0) {
				System.out.println("Sorry, this artist does not have any albums in the music store. Returning to main menu");
				mainMenu();
			} else if(albumArtistList.size() == 1){
				System.out.println("Albums by " + albumArtistList.get(0).getArtist() + ":");
				System.out.println(albumArtistList.get(0).getAlbumName() +  " By: " + albumArtistList.get(0).getArtist());
				System.out.println("	Songs on " + albumArtistList.get(0).getAlbumName());
				ArrayList<Song> songList = albumArtistList.get(0).getSongList();
				for (Song song: songList) {
					System.out.println(" 		" + song.toString());
				}
				System.out.println("Do you want to add this album to your music library? Yes or No");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				if (userInput.equals("yes")) {
					System.out.println("Great! Album will be added to your music library, see updates in see library tab from main menu.");
					lib.addAlbumToLibrary(ms, albumArtistList.get(0).getAlbumName().toLowerCase(), albumArtistList.get(0).getArtist().toLowerCase());
					mainMenu();
				} else {
					System.out.println("Did not want to add album to library, returning to main menu.");
					mainMenu();
				}
			} else if(albumArtistList.size() == 2) {
				System.out.println("Albums by " + albumArtistList.get(0).getArtist() + ":");
				for (Album album: albumArtistList) {
					System.out.println(album.getAlbumName() + " By: " + album.getArtist());
					System.out.println("Songs on " + album.getAlbumName() + ":");
					for (Song song: album.getSongList()) {
						System.out.println("	" + song.toString());
					}
				}
				
				System.out.println("Pick the album you want to focus on.");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				for (Album album: albumArtistList) {
					if (album.getAlbumName().toLowerCase().equals(userInput)) {
						System.out.println("Do you want to add this album to your music library? Yes or No.");
						scanner2 = new Scanner(System.in);
						userInput = scanner2.nextLine().toLowerCase();
						if (userInput.equals("yes")) {
							System.out.println("Great! Album will be added to your music library, see updates in see library tab from main menu.");
							lib.addAlbumToLibrary(ms, album.getAlbumName().toLowerCase(), album.getArtist().toLowerCase());
							mainMenu();
						} else {
							System.out.println("Did not want to add album to library, returning to main menu.");
							mainMenu();
						}
					}
				}
				System.out.println("Sorry, this is not one of the artists albums in the music store. Returning to main menu.");
				mainMenu();
			}
		} else if(userInput.equals("search album by title")) {
			System.out.println("Enter an album title to see if it is in the store:");
			Scanner scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();
			Album album = ms.searchAlbumTitle(userInput);
			if (album == null) {
				System.out.println("Sorry, album was not found in music store. Returning to main menu");
				mainMenu();
			} else {
				System.out.println(album.getAlbumName() + " By: " + album.getArtist());
				System.out.println("Songs on " + album.getAlbumName() + ":");
				for (Song song: album.getSongList()) {
					System.out.println("	" + song.toString());
				}
				System.out.println("Do you want to add this album to your music library? Yes or No");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				if (userInput.equals("yes")) {
					System.out.println("Great! Album will be added to your music library, see updates in see library tab from main menu.");
					lib.addAlbumToLibrary(ms, album.getAlbumName().toLowerCase(), album.getArtist().toLowerCase());
					mainMenu();
				} else {
					System.out.println("Did not want to add album to library, returning to main menu.");
					mainMenu();
				}
			}
		}

	}

	private boolean checkMusicStoreInput(String execution) {
		if (execution.equals("search song by artist")) {
			return true;
		} else if (execution.equals("search song by title")) {
			return true;
		} else if (execution.equals("search album by artist")) {
			return true;
		} else if (execution.equals("search album by title")) {
			return true;
		} else {
			return false;
		}
	}

	private void seeWholeLibrary() {
		System.out.println("Welcome to your library. Here is a list of the things you can view in the library:\n\n");
		System.out.println("Please pick one option to view in your Music Library.\n");
		System.out.println("Playlists   Songs   Albums   Favorite Songs   Artists\n");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine().toLowerCase();
		boolean checkResponse = checkLibraryMenuInput(userInput);
		while (!checkResponse) {
			System.out.println("Invalid Input, please try again.");
			scanner = new Scanner(System.in);
			userInput = scanner.nextLine().toLowerCase();
			checkResponse = checkLibraryMenuInput(userInput);
		}

		if (userInput.equals("playlists")) {
			ArrayList<Playlist> allPlaylists = lib.getAllPlayList();
			if (allPlaylists.size() == 0) {
				System.out.println(
						"You have not created any playlists yet. Returning to main menu and navigate to create a playlist.\n\n");
				mainMenu();
			} else {
				System.out.println("All your playlists: ");
				for (Playlist playlist : allPlaylists) {
					System.out.println(playlist.getPlaylistName());
					System.out.println("	All Songs on " + playlist.getPlaylistName() + ":");
					if (playlist.getUserSongList().size() == 0) {
						System.out.println("		No songs on this playlist yet.");
					} else {
						for (Song song : playlist.getUserSongList()) {
							System.out.println("		" + song.getTitle() + " By: " + song.getArtist() + ", Album: "
									+ song.getAlbum());
						}
					}
				}
				mainMenu();
			}
		} else if (userInput.equals("songs")) {
			ArrayList<String> allSongs = lib.getSongTitles();
			if (allSongs.size() == 0) {
				System.out.println(
						"You haven't added any songs to your library. Returning to main menu and navigate to add songs.\n\n");
				mainMenu();
			} else {
				System.out.println("All Songs in Your Library:\n");
				for (String song : allSongs) {
					System.out.println(song);
				}
				mainMenu();
			}

		} else if (userInput.equals("albums")) {
			ArrayList<Album> allAlbums = lib.getAlbumList();
			if (allAlbums.size() == 0) {
				System.out.println(
						"You haven't added any albums to your library. Returning to main menu and navigate to add an album.\n\n");
				mainMenu();
			} else {
				System.out.println("All Albums in Your Library:\n");
				for (Album album : allAlbums) {
					System.out.println(album.getAlbumName() + " By: " + album.getArtist());
					System.out.println("	All Songs on " + album.getAlbumName() + ":");
					for (Song song : album.getSongList()) {
						System.out.println("			" + song.getTitle() + " By: " + song.getArtist() + ", Album: "
								+ song.getAlbum());
					}
				}
				mainMenu();
			}
		} else if (userInput.equals("favorite songs")) {
			ArrayList<Song> allFavSongs = lib.getFavoriteSongs();
			if (allFavSongs.size() == 0) {
				System.out.println(
						"You haven't added any favorite songs to your library. Returning to main menu and navigate to add favorite songs.\n\n");
				mainMenu();
			} else {
				System.out.println("All Favorite Songs in Your Library:\n");
				for (Song song : allFavSongs) {
					System.out.println(song.getTitle() + " By: " + song.getArtist() + ", Album: " + song.getAlbum());
				}
				mainMenu();
			}
		} else if (userInput.equals("artists")) {
			ArrayList<String> allArtists = lib.getArtists();
			if (allArtists.size() == 0) {
				System.out.println("You have 0 artists in your library. Returning to main menu.\n\n");
				mainMenu();
			} else {
				System.out.println("All Artists in Your Library:\n");
				for (String artist : allArtists) {
					System.out.println(artist);
				}
				mainMenu();
			}
		}
	}

	private boolean checkLibraryMenuInput(String execution) {
		if (execution.equals("playlists")) {
			return true;
		} else if (execution.equals("songs")) {
			return true;
		} else if (execution.equals("albums")) {
			return true;
		} else if (execution.equals("artists")) {
			return true;
		} else if (execution.equals("favorite songs")) {
			return true;
		} else {
			return false;
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

		// songList.add(new Song("DNA.", "Kendrick Lamar", "Damn."));
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

	// PLEASE :(
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
