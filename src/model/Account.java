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

}
