package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import model.MusicStore;
import model.ParseFile;

public class testParseFile {

	private ParseFile pf = new ParseFile("/Users/ethanjholly/Desktop/LA 1/albums");

	@Test
	void testConstructor() {

		assertTrue(this.pf.getMusicStore().getClass() == new MusicStore().getClass());
	}

	@Test
	void testFailIOException() throws Exception {
		// Create an invalid directory path (e.g., non-existent or restricted)
		Path invalidPath = Paths.get("/this/path/does/not/exist");

		// Use reflection to access the private method
		Method method = ParseFile.class.getDeclaredMethod("goThroughFolder", Path.class);
		method.setAccessible(true);

		// Invoke the method and ensure no exception is thrown (it should be caught)
		assertDoesNotThrow(() -> method.invoke(pf, invalidPath));
	}
	
	@Test
	void testFailFileNotFoundException() throws Exception {
		// Create a reference to a non-existent file
		File missingFile = new File("doesNotExist.txt");

		// Use reflection to access the private method
		Method method = ParseFile.class.getDeclaredMethod("addSongToAlbum", File.class);
		method.setAccessible(true);

		// Invoke the method and ensure no exception is thrown (it should be caught)
		assertDoesNotThrow(() -> method.invoke(pf, missingFile));
	}
}