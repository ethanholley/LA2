package test;

import org.junit.jupiter.api.Test;

class testAll {

	@Test
	void testAllCases() throws Exception {
		testRating rating = new testRating();
		testParseFile pf = new testParseFile();
		testSong song = new testSong();
		testMusicStore ms = new testMusicStore();
	}

}