package ua.mainacademy.service;

import org.junit.jupiter.api.Test;
import ua.mainacademy.model.ConnectionInfo;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void filterLines() {
        ConnectionInfo connectionInfoOld = new ConnectionInfo(123, 0L, "test ip");
        ConnectionInfo connectionInfoNew = new ConnectionInfo(321, new Date().getTime(), "test ip");

        FileManager.writeConnectionInfoToFile(connectionInfoOld, "testFile", true);
        FileManager.writeConnectionInfoToFile(connectionInfoNew, "testFile", true);

        List<ConnectionInfo> elements = FileManager.readConnectionInfoFromFile("testFile");
        assertEquals(2, elements.size());

        FileManager.filterLines("testFile", 12345L, (new Date().getTime() + 123456L));

        elements = FileManager.readConnectionInfoFromFile("testFile");
        assertEquals(1, elements.size());
    }

    @Test
    public void getSum() {
        int result = FileManager.getSum(15, 25);
        assertEquals(40, result);
    }
}