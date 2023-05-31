package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	private TestFacadeImplementation testFacadeImplementation;

@BeforeEach
public void setUp() {
    testFacadeImplementation = new TestFacadeImplementation();
}

@Test
public void testGetAllTests() {
    List<Test> allTests = testFacadeImplementation.getAllTests();
    assertNotNull(allTests);
    assertFalse(allTests.isEmpty());
}

@Test
public void testGetTestById() {
    Test test = testFacadeImplementation.getTestById(1);
    assertNotNull(test);
    assertEquals(1, test.getId());
}

@Test
public void testAddTest() {
    Test test = new Test(1, "Test 1");
    assertTrue(testFacadeImplementation.addTest(test));
}

@Test
public void testUpdateTest() {
    Test test = new Test(1, "Updated Test");
    assertTrue(testFacadeImplementation.updateTest(test));
}

@Test
public void testDeleteTest() {
    assertTrue(testFacadeImplementation.deleteTest(1));
}

}
