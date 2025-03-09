package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Genre;

public class testGenre {

	Genre genreAlt = Genre.ALTERNATIVE;
	Genre genrePop = Genre.POP;
	Genre genreSinger = Genre.SINGER_SONGWRITER;
	Genre genreCountry = Genre.TRADITIONAL_COUNTRY;
	Genre genreRock = Genre.ROCK;
	Genre genreLatin = Genre.LATIN;

	@Test
	void testGetDisplayName() {
		assertTrue(genreAlt.getDisplayName().equals("Alternative"));
		assertTrue(genrePop.getDisplayName().equals("Pop"));
		assertTrue(genreSinger.getDisplayName().equals("Singer/Songwriter"));
		assertTrue(genreCountry.getDisplayName().equals("Traditional Country"));
		assertTrue(genreRock.getDisplayName().equals("Rock"));
		assertTrue(genreLatin.getDisplayName().equals("Latin"));

	}

	@Test
	void testFromString() {
		assertTrue(Genre.fromString(null) == null);
		assertTrue(Genre.fromString("Not a GEnre") == null);

		assertTrue(Genre.fromString("alterNative").isEquals(genreAlt));
		assertTrue(Genre.fromString("PoP").isEquals(genrePop));
		assertTrue(Genre.fromString("Singer/SonGwriter").isEquals(genreSinger));
		assertTrue(Genre.fromString("TraditioNal Country").isEquals(genreCountry));
		assertTrue(Genre.fromString("RocK").isEquals(genreRock));
		assertTrue(Genre.fromString("LaTin").isEquals(genreLatin));

	}

	@Test
	void testIsEquals() {
		assertFalse(this.genreAlt.isEquals(null));
		assertTrue(this.genreAlt.isEquals(genreAlt));
		assertTrue(this.genreAlt.isEquals(Genre.ALTERNATIVE));
		assertFalse(this.genreAlt.isEquals(Genre.ROCK));


	}

}
