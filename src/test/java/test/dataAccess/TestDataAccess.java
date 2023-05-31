package test.dataAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;

public class TestDataAccess {
	private DataAccess dataAccess;

@BeforeEach
public void setUp() {
    dataAccess = new DataAccess();
}

@Test
public void testGetAllData() {
    List<Data> allData = dataAccess.getAllData();
    assertNotNull(allData);
    assertFalse(allData.isEmpty());
}

@Test
public void testGetDataById() {
    Data data = dataAccess.getDataById(1);
    assertNotNull(data);
    assertEquals(1, data.getId());
}

@Test
public void testAddData() {
    Data data = new Data(1, "Data 1");
    assertTrue(dataAccess.addData(data));
}

@Test
public void testUpdateData() {
    Data data = new Data(1, "Updated Data");
    assertTrue(dataAccess.updateData(data));
}

@Test
public void testDeleteData() {
    assertTrue(dataAccess.deleteData(1));
}
}

