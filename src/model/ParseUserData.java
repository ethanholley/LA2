package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParseUserData {

	public static LibraryModel parseData(String filename) throws FileNotFoundException {

		System.out.println(filename);

		ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
		LibraryModel lib = new LibraryModel();
		MusicStore ms = pf.getMusicStore();
		HashMap<String, Song> hm = new HashMap<String, Song>();

		File file = new File(filename);

		Scanner scanner = new Scanner(file);
		String search = scanner.nextLine();

		while (scanner.hasNextLine()) {

			String currentLine = scanner.nextLine();

			if (currentLine.equals("ALBUM")) {
				search = currentLine;
				currentLine = scanner.nextLine();
			} else if (currentLine.equals("PLAYLIST")) {
				search = currentLine;
				currentLine = scanner.nextLine();
			}

			String[] strArray = currentLine.split(",");

			if (search.equals("SONGS")) {

				String title = strArray[0];
				String artist = strArray[1];
				String album = strArray[2];
				String genre = strArray[3];
				Genre genreType = Genre.fromString(genre);
				String favorite = strArray[4];
				String rating = strArray[5];

				Song song = new Song(title, artist, album, genreType);
				if (favorite.equals("true")) {
					song.setFavorite();
					ms.setFavoriteOfSong(artist, title);
				}

				Rating ratingType = Rating.valueOf(rating);
				song.setRating(ratingType);
				ms.setRatingOfSong(artist, title, ratingType);

				lib.addSongToLibrary(ms, title.toLowerCase(), artist.toLowerCase());

				hm.put(title.toLowerCase() + " " + artist.toLowerCase(), song);
//				System.out.println("KRUEGER");

			} else if (search.equals("ALBUM")) {
				String albumTitle = strArray[0];
				String artist = strArray[1];
				lib.addAlbumToArrayList(ms, albumTitle.toLowerCase(), artist.toLowerCase());

//				ArrayList<Song> songList = new ArrayList<Song>();
//
//				for (int i = 2; i < strArray.length; i++) {
//					Song song = hm.get(strArray[i] + " " + artist);
//					songList.add(song);
//				}
//				lib.
			} else {
				String plName = strArray[0];
				lib.createPlayList(plName);
				if (strArray.length >= 3) {
					for (int i = 1; i < strArray.length; i += 2) {
						Song song = hm.get(strArray[i] + " " + strArray[i + 1]);
						lib.addSongToPlaylist(plName, song.getTitle().toLowerCase(), song.getArtist().toLowerCase(),
								song.getAlbum().toLowerCase(), song.getGenre());
					}
				}
			}

		}

		return lib;
	}

	public static void main(String[] args) throws FileNotFoundException {
		LibraryModel lib = ParseUserData
				.parseData("/Users/chancekrueger/Desktop/AccountFolder/AccountData/chanceAcct.txt");

		ArrayList<Song> sl = (lib.getAllSongs());
		ArrayList<Album> al = (lib.getAlbumList());
		ArrayList<Playlist> pl = (lib.getAllPlayList());

		System.out.println("\nSONGS:");
		for (Song song : sl) {
			System.out.println("	" + song.toString());
		}

		System.out.println("\nALBUMS:");
		for (Album a : al) {
			System.out.println("	" + a.getAlbumName());
		}

		System.out.println("\nPLAYLISTS:");
		for (Playlist pll : pl) {
			System.out.println("	" + pll.getUserSongList().toString());
		}
	}

}
