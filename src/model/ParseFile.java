package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseFile {

	private MusicStore musicStore;

	public ParseFile(Path file) {
		this.musicStore = new MusicStore();
		goThroughFolder(file);
	}

	public MusicStore getMusicStore() {
		return musicStore;
	}

	/**
	 * Iterates through the given folder and processes each file. If the file is not
	 * "albums.txt", it is passed to addSongToAlbum() for parsing.
	 *
	 * @param path The directory path containing album files.
	 */
	private void goThroughFolder(Path path) {
		try (DirectoryStream<Path> fileList = Files.newDirectoryStream(path)) {
			for (Path filePath : fileList) {
				File file = filePath.toFile();
				String check = String.valueOf(file);

				// Skip "albums.txt" and process other files
				if (!check.contains("albums.txt")) {
					addSongToAlbum(file);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses a given album file to extract song data, creating an Album instance.
	 * The first line contains album and artist information, and subsequent lines
	 * contain song titles.
	 *
	 * @param filePath The file representing an album to be processed.
	 * 
	 *
	 */
	private void addSongToAlbum(File filePath) {

		String artist = "";
		String albumName = "";

		ArrayList<Song> songList = new ArrayList<>();

		try {
			boolean firstLine = true;

			Scanner scanner = new Scanner(filePath);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (firstLine) { // First line contains album and artist details

					String[] split = line.split(",");

					artist += split[1]; // Extract artist name
					albumName += split[0]; // Extract album name

					firstLine = false;
				} else {
					// Add song to the list with extracted artist name
					songList.add(new Song(line, artist));
				}
			}

			scanner.close(); // Close scanner to avoid resource leak

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Album album = new Album(albumName, artist, songList);
		System.out.println(songList.toString());

		// TODO: Uncomment and implement methods in MusicStore
//		this.musicStore.addAlbum(albumName);
//		this.musicStore.addArtist(artist);
//		this.musicStore.addSongList(new ArrayList<>(songList));
	}

	public static void main(String[] args) {
		Path filePath = Paths.get("/Users/chancekrueger/Desktop/albums");
		ParseFile pf = new ParseFile(filePath);
	}

}
