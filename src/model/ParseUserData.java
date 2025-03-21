package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseUserData {

	public static LibraryModel parseData(String filename) throws FileNotFoundException {

		System.out.println(filename);

		LibraryModel lib = new LibraryModel();
		MusicStore ms = new MusicStore();

		File file = new File(filename);

		Scanner scanner = new Scanner(file);
		String search = scanner.nextLine();

		while (scanner.hasNextLine()) {

			String currentLine = scanner.nextLine();

			if (currentLine.equals("ALBUM")) {
				currentLine = scanner.nextLine();
				search = currentLine;
				System.out.println("ALBUM");
			} else if (currentLine.equals("PLAYLIST")) {
				currentLine = scanner.nextLine();
				search = currentLine;
				System.out.println("PLAYLIST");
			}

			System.out.println(currentLine);

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

				lib.addSongToLibrary(ms, title, artist);

			} else if (search.equals("ALBUM")) {
				String albumTitle = strArray[0];
				String artist = strArray[1];
				ArrayList<Song> songList = new ArrayList<Song>();
				for (int i = 2; i < strArray.length; i++) {
//					ms.search
				}

			} else {

			}

		}

		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ParseUserData.parseData("/Users/chancekrueger/Desktop/AccountFolder/AccountData/chanceAcct.txt");
	}

}
