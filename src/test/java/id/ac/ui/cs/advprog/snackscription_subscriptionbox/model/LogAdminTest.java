package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogAdminTest {

    @Test
    void testNoArgsConstructor() {
        LogAdmin logAdmin = new LogAdmin();
        assertNotNull(logAdmin);
    }

    @Test
    void testAllArgsConstructor() {
        String logString = "Test log string";
        String subBoxId = "Test sub box ID";
        LocalDateTime beforeCreation = LocalDateTime.now();

        LogAdmin logAdmin = new LogAdmin(logString, subBoxId);

        assertNotNull(logAdmin);
        assertEquals(logString, logAdmin.getLogString());
        assertEquals(subBoxId, logAdmin.getSubBoxId());
        assertTrue(logAdmin.getDate().isAfter(beforeCreation) || logAdmin.getDate().isEqual(beforeCreation));
    }

    @Test
    void testSettersAndGetters() {
        LogAdmin logAdmin = new LogAdmin();

        logAdmin.setId(1);
        logAdmin.setLogString("Updated log string");
        logAdmin.setSubBoxId("Updated sub box ID");
        LocalDateTime updatedDate = LocalDateTime.now();
        logAdmin.setDate(updatedDate);

        assertEquals(1, logAdmin.getId());
        assertEquals("Updated log string", logAdmin.getLogString());
        assertEquals("Updated sub box ID", logAdmin.getSubBoxId());
        assertEquals(updatedDate, logAdmin.getDate());
    }
}
