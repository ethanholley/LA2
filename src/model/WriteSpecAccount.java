package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WriteSpecAccount {

	private String filePath;
	private Account account;
	private File file;

	public WriteSpecAccount(Account account) {

		this.account = account;

		try {
			file = new File("/Users/chancekrueger/Desktop/AccountFolder/AccountData",
					account.getUsername() + "Acct.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

		} catch (IOException e) {
			System.err.println("An error occurred while Accessing the file: " + e.getMessage());
		}
	}

	public void writeData() {
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("SONGS");
			bw.newLine();

			ArrayList<Object> dataList = this.account.getDataList();
			@SuppressWarnings("unchecked")
			ArrayList<Song> songList = (ArrayList<Song>) dataList.get(0);

			for (Song song : songList) {
				String finalStr = song.getTitle() + "," + song.getArtist() + "," + song.getAlbum();
				finalStr += "," + song.getGenre() + "," + song.isFavorite() + "," + song.getRating();
				bw.append(finalStr);
				bw.newLine();
			}

			bw.newLine();
			bw.append("ALBUM");
			bw.newLine();
			@SuppressWarnings("unchecked")
			HashMap<Album, ArrayList<Song>> albumHash = (HashMap<Album, ArrayList<Song>>) dataList.get(1);

			for (Map.Entry<Album, ArrayList<Song>> entry : albumHash.entrySet()) {

				Album album = entry.getKey();
				ArrayList<Song> sList = entry.getValue();

				String finalStr = album.getAlbumName() + "," + album.getArtist() + ",";
				for (Song song : sList) {
					finalStr += song.getTitle() + "," + song.getArtist() + ",";
				}
				bw.append(finalStr);
				bw.newLine();
			}

			bw.newLine();
			bw.append("PLAYLIST");
			bw.newLine();
			@SuppressWarnings("unchecked")
			ArrayList<Playlist> plList = (ArrayList<Playlist>) dataList.get(2);
			for (Playlist pl : plList) {
				String finalStr = pl.getPlaylistName() + ",";
				for (Song song : pl.getUserSongList()) {
					finalStr += song.getTitle() + "," + song.getArtist() + ",";
				}
				bw.append(finalStr);
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		Account acct = new Account("chance", "pw");
		LibraryModel lib = new LibraryModel();
		ParseFile pf = new ParseFile("/Users/chancekrueger/Desktop/albums");
		MusicStore ms = pf.getMusicStore();
		ms.setRatingOfSong("adele", "best for last", Rating.FOUR);
		lib.addSongToLibrary(ms, "best for last", "adele");
		lib.addAlbumToLibrary(ms, "19", "adele");
		lib.addAlbumToLibrary(ms, "21", "adele");

		lib.createPlayList("fav");
		lib.createPlayList("recents");
		lib.addSongToLibrary(ms, "politik", "coldplay");
		lib.addAlbumToArrayList(ms, "a rush of blood to the head", "coldplay");
		lib.addSongToPlaylist("fav", "tired", "adele", "19", Genre.POP);

		acct.setDataList(lib);

//		System.out.println("__________");
//		System.out.println(lib.getSongTitles());

		WriteSpecAccount aw = new WriteSpecAccount(acct);
		aw.writeData();
		System.out.println("HELLOWORLD");
	}
}
