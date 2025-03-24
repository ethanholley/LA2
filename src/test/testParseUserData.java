package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.LibraryModel;
import model.ParseUserData;

public class testParseUserData {

	@Test
	void testParseData() throws FileNotFoundException {

		ParseUserData pud = new ParseUserData();
		ParseUserData.parseData("/Users/ethanjholly/Desktop/AccountFolder/AccountData/testAcct.txt");

		LibraryModel lib = ParseUserData
				.parseData("/Users/ethanjholly/Desktop/AccountFolder/AccountData/testAcct.txt");
		assertTrue(lib.getAllSongs().size() == 25);
		assertTrue(lib.getAlbumList().size() == 3);
		assertTrue(lib.getAllPlayList().size() == 2);

		LibraryModel lib2 = ParseUserData
				.parseData("/Users/ethanjholly/Desktop/AccountFolder/AccountData/testEmptyAcct.txt");
		assertTrue(lib2.getAllSongs().size() == 0);
		assertTrue(lib2.getAlbumList().size() == 0);
		assertTrue(lib2.getAllPlayList().size() == 0);

		LibraryModel lib3 = ParseUserData
				.parseData("/Users/ethanjholly/Desktop/AccountFolder/AccountData/testEmptyFileAcct.txt");
		assertTrue(lib3.getAllSongs().size() == 0);
		assertTrue(lib3.getAlbumList().size() == 0);
		assertTrue(lib3.getAllPlayList().size() == 0);
	}

}


