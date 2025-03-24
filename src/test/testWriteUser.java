package test;

import static org.junit.jupiter.api.Assertions.*;

import model.WriteUser;
import model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

class TestWriteUser {

    private static final String TEST_FILE_PATH = "test_users.txt";

    @Test
    void testAddUser() {
        WriteUser writeUser = new WriteUser();
        boolean success = writeUser.addUser("testUser", "testPass");
        
        assertTrue(success, "User should be added successfully");
        assertFalse(writeUser.addUser("testUser", "anotherPass"), "Duplicate username should not be allowed");
    }

    @Test
    void testLogin() {
        WriteUser writeUser = new WriteUser();
        writeUser.addUser("testUser", "testPass");

        Account user = writeUser.login("testUser", "testPass");
        assertNotNull(user, "Login should succeed with correct credentials");
        assertNull(writeUser.login("testUser", "wrongPass"), "Login should fail with incorrect password");
        assertNull(writeUser.login("nonExistentUser", "testPass"), "Login should fail for non-existent user");
    }

    @Test
    void testSaveAndLoadUsers() throws IOException {
        File originalFile = new File("/Users/ethanjholly/Desktop/AccountFolder/allAcounts.txt");
        File backupFile = new File("backup_users.txt");

        // Backup the original file
        if (originalFile.exists()) {
            Files.copy(originalFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            originalFile.delete();
        }

        // Create a new WriteUser and add users
        WriteUser writeUser = new WriteUser();
        writeUser.addUser("testUser1", "password1");
        writeUser.addUser("testUser2", "password2");

        // Save users to file
        writeUser.logout();

        // Create a new instance and load users
        WriteUser newWriteUser = new WriteUser();
        newWriteUser.loadUsersFromFile();

        Set<String> usernames = newWriteUser.getAllUsernames();
        assertTrue(usernames.contains("testUser1"), "User testUser1 should be loaded");
        assertTrue(usernames.contains("testUser2"), "User testUser2 should be loaded");

        // Restore original file after test
        if (backupFile.exists()) {
            Files.copy(backupFile.toPath(), originalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            backupFile.delete();
        }
    }
}
