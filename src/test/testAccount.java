package test;

import static org.junit.jupiter.api.Assertions.*;
import model.Account;
import model.LibraryModel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class testAccount {
	
	@Test
    void testAccountCreation() {
        Account account = new Account("testUser", "testPass");
        assertEquals("testUser", account.getUsername());
        assertEquals("testPass", account.getPassword());
    }
	
	@Test
    void testInitialDataListIsEmpty() {
        Account account = new Account("testUser", "testPass");
        assertNotNull(account.getDataList(), "Data list should not be null");
        assertEquals(0, account.getDataList().size(), "Data list should initially be empty");
    }
	
	@Test
    void testSetDataList() {
        Account account = new Account("testUser", "testPass");
        LibraryModel library = new LibraryModel();

        account.setDataList(library);

        ArrayList<Object> dataList = account.getDataList();

        assertEquals(3, dataList.size());
        assertNotNull(dataList.get(0));
        assertNotNull(dataList.get(1));
        assertNotNull(dataList.get(2));
    }

}
