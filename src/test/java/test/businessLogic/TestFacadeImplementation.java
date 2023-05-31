package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	private BLFacadeImplementation blFacadeImplementation;

@BeforeEach
public void setUp() {
    blFacadeImplementation = new BLFacadeImplementation();
}

@Test
public void testGetAllData() {
    List<Data> allData = blFacadeImplementation.getAllData();
    assertNotNull(allData);
    assertFalse(allData.isEmpty());
}

@Test
public void testGetDataById() {
    Data data = blFacadeImplementation.getDataById(1);
    assertNotNull(data);
    assertEquals(1, data.getId());
}

@Test
public void testAddData() {
    Data data = new Data(1, "Data 1");
    assertTrue(blFacadeImplementation.addData(data));
}

@Test
public void testUpdateData() {
    Data data = new Data(1, "Updated Data");
    assertTrue(blFacadeImplementation.updateData(data));
}

@Test
public void testDeleteData() {
    assertTrue(blFacadeImplementation.deleteData(1));
}


}
