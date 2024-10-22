package org.lab3b;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DatabaseTest {

    private Database mockDatabase;
    private Service service;

    @BeforeEach
    void setUp() {
        // Create a mock of the Database class
        mockDatabase = Mockito.mock(Database.class);

        // Inject the mock into the Service
        service = new Service(mockDatabase);
    }

    @Test
    void testQueryWhenDatabaseIsAvailable() {
        // Mock the behavior of the isAvailable method to return true
        when(mockDatabase.isAvailable()).thenReturn(true);

        // Call the query method
        boolean result = service.query("SELECT * FROM users");

        // Ensure the result is true (since the database is mocked to be available)
        assertTrue(result);

        // Ensure isAvailable() was called once
        verify(mockDatabase, times(1)).isAvailable();
    }

    @Test
    void testGetDatabaseID() {
        // Mock the getUniqueId method to return a specific value
        when(mockDatabase.getUniqueId()).thenReturn(123);

        // Call the getDatabaseID method
        String databaseId = service.getDatabaseID();

        // Ensure the result contains the mocked unique ID
        assertEquals("Using database with id: 123", databaseId);

        // Ensure getUniqueId() was called once
        verify(mockDatabase, times(1)).getUniqueId();
    }
    @Test
    void testGetUniqueId() {
        // Arrange
        Database database = new Database();

        // Act
        int uniqueId = database.getUniqueId();

        // Assert
        assertEquals(42, uniqueId, "getUniqueId() should return 42");
    }
}
