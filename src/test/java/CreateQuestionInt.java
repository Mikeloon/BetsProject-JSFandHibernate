import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class CreateQuestionInt {
	 private CreateQuestionInt createQuestionInt;

@BeforeEach
public void setUp() {
    createQuestionInt = new CreateQuestionInt();
}

@Test
public void testCalculateMaxPoints() {
    assertEquals(10, createQuestionInt.calculateMaxPoints(5, 2));
    assertEquals(0, createQuestionInt.calculateMaxPoints(0, 0));
    assertEquals(15, createQuestionInt.calculateMaxPoints(7, 3));
}

@Test
public void testGetQuestionById() {
    assertNotNull(createQuestionInt.getQuestionById(1));
    assertNull(createQuestionInt.getQuestionById(5));
}

@Test
public void testAddQuestion() {
    assertTrue(createQuestionInt.addQuestion("Question 1", "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionInt.addQuestion(null, "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionInt.addQuestion("Question 2", null, "Option 2", 2, 10));
    assertFalse(createQuestionInt.addQuestion("Question 3", "Option 1", null, 2, 10));
    assertFalse(createQuestionInt.addQuestion("Question 4", "Option 1", "Option 2", -1, 10));
    assertFalse(createQuestionInt.addQuestion("Question 5", "Option 1", "Option 2", 2, -1));
}

@Test
public void testDeleteQuestion() {
    assertTrue(createQuestionInt.deleteQuestion(1));
    assertFalse(createQuestionInt.deleteQuestion(5));
}
	
}
