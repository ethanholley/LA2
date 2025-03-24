package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParseUserData {

	public static LibraryModel parseData(String filename) throws FileNotFoundException {
		ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");
		LibraryModel lib = new LibraryModel();
		MusicStore ms = pf.getMusicStore();
		HashMap<String, Song> hm = new HashMap<String, Song>();

		File file = new File(filename);

		Scanner scanner = new Scanner(file);

		if (!scanner.hasNext()) {
			return lib;
		}

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
				if (strArray.length == 6) {

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
						ms.setFavoriteOfSong(artist.toLowerCase(), title.toLowerCase());
					}

					Rating ratingType = Rating.valueOf(rating);

					song.setRating(ratingType);
					ms.setRatingOfSong(artist.toLowerCase(), title.toLowerCase(), ratingType);

					lib.addSongToLibrary(ms, title.toLowerCase(), artist.toLowerCase());

					hm.put(title.toLowerCase() + " " + artist.toLowerCase(), song);
				}

			} else if (search.equals("ALBUM")) {
				if (strArray.length > 2) {
					String albumTitle = strArray[0];
					String artist = strArray[1];
					lib.addAlbumToArrayList(ms, albumTitle.toLowerCase(), artist.toLowerCase());
				}
			} else {
				String plName = strArray[0];
				lib.createPlayList(plName);
				if (strArray.length >= 3) {
					for (int i = 1; i < strArray.length; i += 2) {
						Song song = hm.get((strArray[i] + " " + strArray[i + 1]).toLowerCase());
						lib.addSongToPlaylist(plName.toLowerCase(), song.getTitle().toLowerCase(),
								song.getArtist().toLowerCase(), song.getAlbum().toLowerCase(), song.getGenre());
					}
				}
			}

		}

		return lib;
	}

	
}
