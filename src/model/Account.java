package model;

import java.util.ArrayList;

public class Account {

	private String username;
	private String password;
	private ArrayList<Object> dataList;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		this.dataList = new ArrayList<Object>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Object> getDataList() {
		return dataList;
	}

	public void setDataList(LibraryModel lib) {
		this.dataList.removeAll(dataList);
		this.dataList.add(lib.getAllSongs());
		this.dataList.add(lib.getAlbumHash());
		this.dataList.add(lib.getAllPlayList());
	}

	public static void main(String[] args) {
		Account acct = new Account("chance", "pw");
		LibraryModel lib = new LibraryModel();
		ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");
		MusicStore ms = pf.getMusicStore();
		acct.setDataList(lib);

		System.out.println(acct.getDataList().toString());

		lib.createPlayList("FAV");
		lib.createPlayList("RECENTS");
		lib.addAlbumToArrayList(ms, "19", "adele");
		lib.addSongToPlaylist("fav", "tired", "adele", "19", Genre.POP);

		acct.setDataList(lib);
		System.out.println(acct.getDataList().toString());
	}

}
