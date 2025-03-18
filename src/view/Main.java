
package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.*;

public class Main {
	private MusicStore ms;
	private LibraryModel lib;

	/*
	 * Constructor for Main class. Initializes the music store and library, adds an
	 * album to the library, and starts the main menu.
	 */
	public Main() {
		ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");
		ms = pf.getMusicStore();
		lib = new LibraryModel();
		lib.createPlayList("Favorite Songs"); // create automatic playlists for fav and top rated songs
		lib.createPlayList("Top Rated");
		mainMenu();
	}

	private void mainMenu() {
		System.out.println("Welcome to Your Music Aplication.\n");
		System.out.println("Choose one of the options below in the Console\n");
		checkGenrePlaylist();
		System.out.println("Search Music Store     See Library     Search Library     Create Playlist\n");
		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();
		pickDestination(execution);
	}

	/*
	 * Directs the user to the selected menu option.
	 *
	 * @param execution The user's selected option.
	 */
	private void pickDestination(String execution) {
		if (execution.equals("search music store")) {
			searchMusicStore();
		} else if (execution.equals("see library")) {
			seeWholeLibrary();
		} else if (execution.equals("search library")) {
			searchLibrary();
		} else if (execution.equals("create playlist")) {
			createPlaylist();
		} else {
			System.out.println("Invalid Input, please try again.");
			Scanner scanner = new Scanner(System.in);
			execution = scanner.nextLine().toLowerCase();
			pickDestination(execution);
		}
	}

	/*
	 * Creates a new playlist and prompts the user to add songs.
	 */
	private void createPlaylist() {

		System.out.println("What is the name of the Playlist you would like?\n");

		Scanner scanner = new Scanner(System.in);
		String getName = scanner.nextLine();
		String playlistName = getName.toLowerCase();
		for (Playlist playlist : lib.getAllPlayList()) {
			if (playlist.getPlaylistName().toLowerCase().equals(playlistName)) {
				System.out.println("I'm Sorry, this name is already in your library. Please choose another name.\n\n");
				createPlaylist();
			}
		}

		lib.createPlayList(getName);
		System.out.println("\nPlaylist created successfully. Going back to main menu\n\n");
		mainMenu();
	}
	
	private void checkGenrePlaylist() {
	    ArrayList<String> genreList = lib.getGenreList(); // Get all genres in the library

	    for (String genre : genreList) {
	        ArrayList<Song> songsInGenre = lib.getSongsByGenre(genre); // Get all songs in this genre

	        if (songsInGenre.size() >= 10) { // Only create a playlist if at least 10 songs exist
	            String playlistName = genre.substring(0, 1).toUpperCase() + genre.substring(1) + " Playlist"; // Format name
	            Playlist genrePlaylist = null;
	            
	            // Check if the playlist already exists
	            for (Playlist playlist : lib.getAllPlayList()) {
	                if (playlist.getPlaylistName().equalsIgnoreCase(playlistName)) {
	                    genrePlaylist = playlist;
	                    break;
	                }
	            }

	            // If the playlist does not exist, create it
	            if (genrePlaylist == null) {
	                lib.createPlayList(playlistName);
	                genrePlaylist = lib.searchPlaylistByName(playlistName); // Retrieve the new playlist
	            }

	            // Add missing songs to the playlist
	            for (Song song : songsInGenre) {
	                if (!genrePlaylist.getUserSongList().contains(song)) {
	                    lib.addSongToPlaylist(playlistName, song.getTitle(), song.getArtist(), song.getAlbum(), song.getGenre());
	                }
	            }
	        }
	    }
	}

	/*
	 * Displays the Music Store menu.
	 */
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
			System.out.println("You can add the song to your library, mark as a favorite, add it to a playlist, or rate the song.");
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
			System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist   Rate the Song");
			scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();

			if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
					&& !userInput.equals("add it to playlist") && !userInput.equals("rate the song")) {
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
				lib.addSongToPlaylist("Favorite Songs", song.getTitle(), song.getArtist(), song.getAlbum(), song.getGenre());
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
					String input = scanner2.nextLine().toLowerCase();
					boolean found = false;
					for (Playlist playlist : allPlaylists) {
						if (playlist.getPlaylistName().toLowerCase().equals(input)) {
							found = true;
							System.out.println(
									"Great! Adding " + song.getTitle() + " to " + playlist.getPlaylistName() + "!");
							System.out.println("Going back to main menu");
							lib.addSongToPlaylist(playlist.getPlaylistName().toLowerCase(), song.getTitle(),
									song.getArtist(), song.getAlbum(), song.getGenre());
							// mainMenu();
							break;
						}
					}
					if (!found) {
						System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
					}
					mainMenu();
				}
			} else if (userInput.equals("rate the song")) {
				checkRating(song);
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
				System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist   Rate the Song");
				scanner2 = new Scanner(System.in);
				userInput = scanner.nextLine().toLowerCase();
				if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
						&& !userInput.equals("add it to playlist") && !userInput.equals("rate the song")) {
					System.out.println("Sorry, this is not one of the menu options, returning to main menu.");
					mainMenu();
				}

				if (userInput.equals("add song to library")) {
					System.out.println("Great! Adding " + songList.get(0).getTitle() + " to your music library.");
					System.out.println("Going back to main menu. Go to see library to see changes.");
					lib.addSongToLibrary(ms, songList.get(0).getTitle().toLowerCase(),
							songList.get(0).getArtist().toLowerCase());
					mainMenu();
				} else if (userInput.equals("mark song as favorite")) {
					System.out.println("Great! Adding " + songList.get(0).getTitle() + " as a favorite.");
					System.out.println(
							"Going back to main menu. Go to favorite songs in see library menu option to see changes.");
					ms.setFavoriteOfSong(songList.get(0).getArtist().toLowerCase(),
							songList.get(0).getTitle().toLowerCase());
					lib.addSongToLibrary(ms, songList.get(0).getTitle().toLowerCase(),
							songList.get(0).getArtist().toLowerCase());
					lib.addSongToPlaylist("Favorite Songs", songList.get(0).getTitle(), songList.get(0).getArtist(), songList.get(0).getAlbum(), songList.get(0).getGenre());
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
								lib.addSongToPlaylist(playlist.getPlaylistName().toLowerCase(),
										songList.get(0).getTitle(), songList.get(0).getArtist(),
										songList.get(0).getAlbum(), songList.get(0).getGenre());
								mainMenu();
								break;
							}
						}
						if (!found) {
							System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
						}
						mainMenu();
					}
				} else if (userInput.equals("rate the song")) {
					checkRating(songList.get(0));
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
				System.out.println("Add song to library   Mark Song as favorite   Add it to Playlist   Rate the Song");
				scanner2 = new Scanner(System.in);
				userInput = scanner.nextLine().toLowerCase();
				System.out.println(userInput);
				if (!userInput.equals("add song to library") && !userInput.equals("mark song as favorite")
						&& !userInput.equals("add it to playlist") && !userInput.equals("rate the song")) {
					System.out.println("Sorry, this is not one of the menu options, returning to main menu.");
					mainMenu();
				}

				if (userInput.equals("add song to library")) {
					System.out.println("Great! Adding " + chosenSong.getTitle() + " to your music library.");
					System.out.println("Going back to main menu. Go to see library to see changes.");
					lib.addSongToLibrary(ms, chosenSong.getTitle().toLowerCase(), chosenSong.getArtist().toLowerCase());
					mainMenu();

				} else if (userInput.equals("mark song as favorite")) {
					System.out.println("Great! Adding " + chosenSong.getTitle() + " as a favorite.");
					System.out.println(
							"Going back to main menu. Go to favorite songs in see library menu option to see changes.");
					ms.setFavoriteOfSong(chosenSong.getArtist().toLowerCase(), chosenSong.getTitle().toLowerCase());
					lib.addSongToPlaylist("Favorite Songs", chosenSong.getTitle(), chosenSong.getArtist(), chosenSong.getAlbum(), chosenSong.getGenre());
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
						System.out.println(chosenSong.getTitle());
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
								lib.addSongToPlaylist(playlist.getPlaylistName().toLowerCase(), chosenSong.getTitle(),
										chosenSong.getArtist(), chosenSong.getAlbum(), chosenSong.getGenre());
								mainMenu();
								break;
							}
						}
						if (!found) {
							System.out.println("Sorry playlist you entered is not found. Going back to main menu.");
						}
						mainMenu();
					}

				} else if (userInput.equals("rate the song")) {
					checkRating(chosenSong);
				}
			}
		} else if (userInput.equals("search album by artist")) {
			System.out.println("Enter an artist to see all their albums in the store:");
			Scanner scanner2 = new Scanner(System.in);
			userInput = scanner2.nextLine().toLowerCase();
			ArrayList<Album> albumArtistList = ms.searchAlbumArtist(userInput);
			if (albumArtistList.size() == 0) {
				System.out.println(
						"Sorry, this artist does not have any albums in the music store. Returning to main menu");
				mainMenu();
			} else if (albumArtistList.size() == 1) {
				System.out.println("Albums by " + albumArtistList.get(0).getArtist() + ":");
				System.out
						.println(albumArtistList.get(0).getAlbumName() + " By: " + albumArtistList.get(0).getArtist());
				System.out.println("	Songs on " + albumArtistList.get(0).getAlbumName());
				ArrayList<Song> songList = albumArtistList.get(0).getSongList();
				for (Song song : songList) {
					System.out.println(" 		" + song.toString());
				}
				System.out.println("Do you want to add this album to your music library? Yes or No");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				if (userInput.equals("yes")) {
					System.out.println(
							"Great! Album will be added to your music library, see updates in see library tab from main menu.");
					lib.addAlbumToLibrary(ms, albumArtistList.get(0).getAlbumName().toLowerCase(),
							albumArtistList.get(0).getArtist().toLowerCase());
					mainMenu();
				} else {
					System.out.println("Did not want to add album to library, returning to main menu.");
					mainMenu();
				}
			} else if (albumArtistList.size() == 2) {
				System.out.println("Albums by " + albumArtistList.get(0).getArtist() + ":");
				for (Album album : albumArtistList) {
					System.out.println(album.getAlbumName() + " By: " + album.getArtist());
					System.out.println("Songs on " + album.getAlbumName() + ":");
					for (Song song : album.getSongList()) {
						System.out.println("	" + song.toString());
					}
				}

				System.out.println("Pick the album you want to focus on.");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				for (Album album : albumArtistList) {
					if (album.getAlbumName().toLowerCase().equals(userInput)) {
						System.out.println("Do you want to add this album to your music library? Yes or No.");
						scanner2 = new Scanner(System.in);
						userInput = scanner2.nextLine().toLowerCase();
						if (userInput.equals("yes")) {
							System.out.println(
									"Great! Album will be added to your music library, see updates in see library tab from main menu.");
							lib.addAlbumToLibrary(ms, album.getAlbumName().toLowerCase(),
									album.getArtist().toLowerCase());
							mainMenu();
						} else {
							System.out.println("Did not want to add album to library, returning to main menu.");
							mainMenu();
						}
					}
				}
				System.out.println(
						"Sorry, this is not one of the artists albums in the music store. Returning to main menu.");
				mainMenu();
			}
		} else if (userInput.equals("search album by title")) {
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
				for (Song song : album.getSongList()) {
					System.out.println("	" + song.toString());
				}
				System.out.println("Do you want to add this album to your music library? Yes or No");
				scanner2 = new Scanner(System.in);
				userInput = scanner2.nextLine().toLowerCase();
				if (userInput.equals("yes")) {
					System.out.println(
							"Great! Album will be added to your music library, see updates in see library tab from main menu.");
					lib.addAlbumToLibrary(ms, album.getAlbumName().toLowerCase(), album.getArtist().toLowerCase());
					mainMenu();
				} else {
					System.out.println("Did not want to add album to library, returning to main menu.");
					mainMenu();
				}
			}
		}

	}

	private void checkRating(Song song) {
		System.out.println("Enter an integer rating for the song from 1-5.");
		Scanner scanner2 = new Scanner(System.in);
		String userInput = scanner2.nextLine();
		try {
			int ratingValue = Integer.parseInt(userInput); // Convert to integer
			Rating rating = getRatingFromValue(ratingValue); // Convert to enum
			if (rating != null) {
				ms.setRatingOfSong(song.getArtist().toLowerCase(), song.getTitle().toLowerCase(), rating);
				song.setRating(rating);
				System.out.println("You rated the song: " + rating + " - " + song.getTitle() + " By: "
						+ song.getArtist() + ", " + song.getAlbum() + " Rating: " + song.getRating());
				if (rating == rating.FIVE) {
					System.out.println("Song rated a 5/5. Automatically adding " + song.getTitle() + " By: "
							+ song.getArtist() + " to favorite songs in music library.\n");
					ms.setFavoriteOfSong(song.getArtist().toLowerCase(), song.getTitle().toLowerCase());
					lib.addSongToPlaylist("Favorite Songs", song.getTitle(), song.getArtist(), song.getAlbum(), song.getGenre());
					lib.addSongToPlaylist("Top Rated", song.getTitle(), song.getArtist(), song.getAlbum(), song.getGenre());
					lib.addSongToLibrary(ms, song.getTitle().toLowerCase(), song.getArtist().toLowerCase());
				} else {
					System.out.println("Do you want to add this song to your music library? Yes or No?");
					scanner2 = new Scanner(System.in);
					userInput = scanner2.nextLine().toLowerCase();
					if (userInput.equals("yes")) {
						if (rating == rating.FOUR) {
							lib.addSongToPlaylist("Top Rated", song.getTitle(), song.getArtist(), song.getAlbum(), song.getGenre());
						}
						System.out.println("Great! " + song.getTitle() + " By: " + song.getArtist()
								+ " will be added to your library.\n");
						lib.addSongToLibrary(ms, song.getTitle().toLowerCase(), song.getArtist().toLowerCase());
					} else {
						System.out.println("Answer was not yes, returning to main menu...\n");
					}
				}
				mainMenu();
			} else {
				System.out.println("Invalid rating. Did not enter a number from 0 to 5. Returning to main menu\n");
				mainMenu();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Did not enter a numeric value from 0 to 5.\n");
			mainMenu();
		}
	}

	// Helper method to get the Rating enum from an integer
	private static Rating getRatingFromValue(int value) {
		for (Rating r : Rating.values()) {
			if (r.getRating() == value) {
				return r;
			}
		}
		return null; // Return null if no matching Rating is found
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

	/*
	 * Displays the library menu, allowing users to view playlists, songs, albums,
	 * favorite songs, and artists.
	 */
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
			listAllPlaylists();
			mainMenu();
		} else if (userInput.equals("songs")) {
			ArrayList<Song> allSongs = lib.getAllSongs();
			if (allSongs.size() == 0) {
				System.out.println(
						"You haven't added any songs to your library. Returning to main menu and navigate to add songs.\n\n");
				mainMenu();
			} else {
				System.out.println("How do you want your songs listed in order:\n");
				System.out.println("Song Title   Artist   Rating");
				scanner = new Scanner(System.in);
				userInput = scanner.nextLine().toLowerCase();
				if (userInput.equals("song title")) {
					ArrayList<String> allSongTitles = lib.getSongTitles();
					Collections.sort(allSongTitles);
					for (String song : allSongTitles) {
						System.out.println(song);
					}
					System.out.println("\nGoing back to main menu...\n");
					mainMenu();
				} else if (userInput.equals("artist")) {
					Collections.sort(allSongs, Comparator.comparing(Song::getArtist));
					for (Song song : allSongs) {
						System.out.println("Song Title: " + song.getTitle() + "  Artist: " + song.getArtist()
								+ ", Album: " + song.getAlbum());
					}
					System.out.println("\nGoing back to main menu...\n");
					mainMenu();
				} else if (userInput.equals("rating")) {
					Collections.sort(allSongs, Comparator.comparing(Song::getRating)); // sort by rating in ascending
																						// order
					for (Song song : allSongs) {
						if (song.getRating() == Rating.ZERO) {
							System.out.println("Song Title: " + song.getTitle() + "  Artist: " + song.getArtist()
									+ ", Album: " + song.getAlbum() + " Rating: Song not Rated");
						} else {
							System.out.println("Song Title: " + song.getTitle() + "  Artist: " + song.getArtist()
									+ ", Album: " + song.getAlbum() + " Rating: " + song.getRating());
						}
					}
				}
			}
			System.out.println();
			mainMenu();
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
			}
			mainMenu();
		} else if (userInput.equals("favorite songs")) {
			ArrayList<Song> allFavSongs = lib.getFavoriteSongs();
			if (allFavSongs.size() == 0) {
				System.out.println(
						"You haven't added any songs to your library. Returning to main menu and navigate to add favorite songs.\n\n");
				mainMenu();
			} else {
				System.out.println("All Favorite Songs in Your Library:\n");
				for (Song song : allFavSongs) {
					System.out.println(song.getTitle() + " By: " + song.getArtist() + ", Album: " + song.getAlbum());
				}
			}
			mainMenu();
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
			}
		}
		mainMenu();
	}

	/*
	 * Validates the user's input for the library menu.
	 *
	 * @param execution The user's input.
	 * 
	 * @return True if input is valid, false otherwise.
	 */
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

	/*
	 * Searches for an album in the library and displays its songs.
	 */
	private void libraryAlbumSearch() {
		if (lib.getSongTitles().size() == 0) {
			System.out.println("I'm Sorry, There are no Albums in your Library.\n\n");
			mainMenu();
		}
		boolean flag = true;
		String albumInput = "";
		while (flag) {
			System.out.println("What Album are you Searching for?\n\n");
			Scanner scanner = new Scanner(System.in);
			albumInput = scanner.nextLine().toLowerCase();

			for (Album album : lib.getAlbumList()) {
				if (album.getAlbumName().toLowerCase().equals(albumInput)) {
					albumInput = album.getAlbumName();
					flag = false;
					break;
				}
			}

			if (flag) {
				System.out.println("I'm Sorry, I couln't find that Album. Please try again.\n");
			}
		}
		Album album = lib.searchAlbumTitle(albumInput.toLowerCase());
		ArrayList<Song> songList = album.getSongList();
		System.out.println(album.getAlbumName() + ":");
		System.out.println("	Songs:");
		for (Song song : songList) {
			System.out.println("		" + song.getTitle());
		}
		System.out.println("\n\nloading back to Main Menu...\n\n");
		mainMenu();
	}

	/*
	 * Searches for an artist in the library and displays their songs.
	 * 
	 * Prompts the user to enter an artist's name and checks if the artist exists in
	 * the library. If found, displays the artist's songs and allows the user to
	 * select a song.
	 */
	private void libraryArtistSearch() {
		if (lib.getSongTitles().size() == 0) {
			System.out.println("I'm Sorry, There are no Artists in your Library.\n\n");
			mainMenu();
		}
		boolean flag = true;
		String artistInput = "";
		while (flag) {
			System.out.println("What Artist are you Searching for?\n\n");
			Scanner scanner = new Scanner(System.in);
			artistInput = scanner.nextLine().toLowerCase();

			for (String artist : lib.getArtists()) {
				if (artist.toLowerCase().equals(artistInput)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("I'm Sorry, I couln't find that Artist. Please try again.\n");
			}
		}

		ArrayList<Song> songList = lib.searchSongbyArtist(artistInput);
		System.out.println(songList.get(0).getArtist() + ":");
		for (Song song : songList) {
			System.out.println("	" + song.getTitle());
		}
		System.out.println("\nWhat song would you like to select?\n");
		flag = true;
		String songStr = "";
		int index = 0;
		while (flag) {
			Scanner scanner = new Scanner(System.in);
			songStr += scanner.nextLine().toLowerCase();
			index = 0;
			for (Song song : songList) {
				if (song.getTitle().toLowerCase().equals(songStr)) {
					flag = false;
					break;
				}
				index++;
			}
			if (flag) {
				System.out.println("I'm sorry, I couldn't find that song. Please try again.\n");
			}
		}
		librarySearchPlaylist(songList.get(index));
	}

	/*
	 * Searches for a song in the library by title.
	 * 
	 * Prompts the user to enter a song title and retrieves matching songs from the
	 * library. If multiple songs are found, asks the user to select the correct
	 * artist. If no song is found, prompts the user to try again.
	 */
	private void librarySongSearch() {
		if (lib.getSongTitles().size() == 0) {
			System.out.println("I'm Sorry, There are no Songs in your Library.\n\n");
			mainMenu();
		}
		System.out.println("What Song are you Searching for?\n\n");
		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();
		Song song = null;
		ArrayList<Song> songList = lib.searchSongbyTitle(execution);

		if (songList.size() == 0) {
			System.out.println("I'm sorry, I couldn't find that song. Please try again.\n");
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

	/*
	 * Handles actions for a selected song.
	 * 
	 * Provides the user with options for the selected song, such as adding it to a
	 * playlist. If the playlist does not exist, informs the user and redirects them
	 * to the main menu.
	 * 
	 * @param song The selected song.
	 */
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
				lib.addSongToPlaylist(playlistName, song.getTitle(), song.getArtist(), song.getAlbum(),
						song.getGenre());
				System.out.println("Successfully added!\n");
			}
		} else {
			System.out.println("Im Sorry, Invalid Input, Please try again.\n\n");
			librarySearchPlaylist(song);
		}
		System.out.println("loading back to Main Menu...\n\n");
		mainMenu();
	}

	private void removeSongFromPlaylistLibrary() {
		listAllPlaylists();
		System.out.println("From which Playlist would you like to remove a Song from?\n\n");
		boolean flag = true;
		String playlistName = "";
		Scanner scanner = new Scanner(System.in);
		while (flag) {
			playlistName = scanner.nextLine().toLowerCase();
			for (Playlist playlist : lib.getAllPlayList()) {
				if (playlist.getPlaylistName().toLowerCase().equals(playlistName)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("I'm Sorry, I couldn't find that Playlist. Please try again.\n\n");
			}
		}
		Playlist pl = lib.searchPlaylistByName(playlistName);
		if (pl.getUserSongList().size() == 0) {
			System.out.println("No Songs in " + pl.getPlaylistName() + "\nReturning to Main Menu...\n\n");
			mainMenu();
		}
		System.out.println("Songs in " + pl.getPlaylistName() + ":");
		for (Song song : pl.getUserSongList()) {
			System.out.println(song.getTitle() + ": " + song.getArtist());
		}
		System.out.println("What Song do you want to remove?\n\n");
		String songName = scanner.nextLine().toLowerCase();
		System.out.println("What is the Artist of the Song you want to remove?\n\n");
		String artistName = scanner.nextLine().toLowerCase();
		flag = true;
		while (flag) {
			for (Song song : pl.getUserSongList()) {
				if (song.getTitle().toLowerCase().equals(songName)
						&& song.getArtist().toLowerCase().equals(artistName)) {
					songName = song.getTitle();
					artistName = song.getArtist();
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("I'm Sorry, either the Song or Artist was typed Incorrectly. Please try again.\n\n");
				System.out.println("What Song do you want to remove?\n\n");
				songName = scanner.nextLine().toLowerCase();
				System.out.println("What is the Artist of the Song you want to remove?\n\n");
				artistName = scanner.nextLine().toLowerCase();
			}
		}
		lib.removeSongPlaylist(playlistName.toLowerCase(), songName, artistName);
		System.out.println("Successfully removed song from " + pl.getPlaylistName() + ".\n");
		System.out.println("loading back to Main Menu...\n\n");
		mainMenu();
	}

	private void searchLibrary() {
		System.out.println(
				"Welcome to the library Search. Here is a list of the things you can search in the library:\n\n");
		System.out.println("Please pick one options to search by.\n");
		System.out.println("Song     Artist     Album     Genre     Remove Song from a Playlist\n");
		Scanner scanner = new Scanner(System.in);
		String execution = scanner.nextLine().toLowerCase();
		boolean flag = true;
		while (flag) {
			if (execution.equals("song")) {
				librarySongSearch();
				flag = false;
			} else if (execution.equals("artist")) {
				libraryArtistSearch();
				flag = false;
			} else if (execution.equals("album")) {
				libraryAlbumSearch();
				flag = false;
			} else if (execution.equals("genre")) {
				libraryGenreSearch();
				flag = false;
			} else if (execution.equals("remove song from a playlist")) {
				removeSongFromPlaylistLibrary();
				flag = false;
			} else {
				System.out.println("Invalid Input, please try again.");
				Scanner newScanner = new Scanner(System.in);
				execution = newScanner.nextLine().toLowerCase();
			}
		}
	}

	private void libraryGenreSearch() {

		ArrayList<String> genreList = this.lib.getGenreList();
		Scanner scanner = new Scanner(System.in);

		if (genreList.size() == 0) {
			System.out.println("No Songs Added to the Library, add songs to Library.\n");
			System.out.println("loading back to Main Menu...\n\n");
			mainMenu();

		}

		System.out.println("\nHere is the List of Genres added to the Library\n\n");

		for (String genre : genreList) {
			System.out.println(genre);
		}

		System.out.println("\nWhat is the Genre of the Song you are looking for?\n\n");

		boolean flag = true;
		String genreInput = "";

		while (flag) {
			genreInput = scanner.nextLine().toLowerCase();
			for (String genre : genreList) {
				if (genre.equals(genreInput)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("I'm Sorry, I couldn't find that Genre. Please try again.\n\n");
			}
		}

		ArrayList<Song> songList = this.lib.getSongsByGenre(genreInput);

		System.out.println("Here is the list of all the Songs with " + genreInput + " as the Genre:\n");

		for (Song song : songList) {
			System.out.println(song.toString());
		}

		System.out.println("What Song are you Searching for?\n");

		Song songInput = null;

		while (songInput == null) {
			String songStr = scanner.nextLine().toLowerCase();
			for (Song song : songList) {
				if (song.getTitle().toLowerCase().equals(songStr)) {
					songInput = new Song(song);
				}
			}
			if (songInput == null) {
				System.out.println("I'm Sorry, I couldn't find that Song. Please try again.\n\n");
			}
		}

		librarySearchPlaylist(songInput);
	}

	private void listAllPlaylists() {
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
			System.out.println("\nReturning to Main Menu...\n\n");
		}
	}

	/*
	 * The main entry point of the program.
	 * 
	 * Creates an instance of the Main class and starts the application.
	 * 
	 */
	public static void main(String[] args) {
		Main m = new Main();
	}
}
