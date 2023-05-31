import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class CreateQuestionDAW {

	private CreateQuestionDAW createQuestionDAW;

@BeforeEach
public void setUp() {
    createQuestionDAW = new CreateQuestionDAW();
}

@Test
public void testCalculateMaxPoints() {
    assertEquals(10, createQuestionDAW.calculateMaxPoints(5, 2));
    assertEquals(0, createQuestionDAW.calculateMaxPoints(0, 0));
    assertEquals(15, createQuestionDAW.calculateMaxPoints(7, 3));
}

@Test
public void testGetQuestionById() {
    assertNotNull(createQuestionDAW.getQuestionById(1));
    assertNull(createQuestionDAW.getQuestionById(5));
}

@Test
public void testAddQuestion() {
    assertTrue(createQuestionDAW.addQuestion("Question 1", "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionDAW.addQuestion(null, "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionDAW.addQuestion("Question 2", null, "Option 2", 2, 10));
    assertFalse(createQuestionDAW.addQuestion("Question 3", "Option 1", null, 2, 10));
    assertFalse(createQuestionDAW.addQuestion("Question 4", "Option 1", "Option 2", -1, 10));
    assertFalse(createQuestionDAW.addQuestion("Question 5", "Option 1", "Option 2", 2, -1));
}

@Test
public void testDeleteQuestion() {
    assertTrue(createQuestionDAW.deleteQuestion(1));
    assertFalse(createQuestionDAW.deleteQuestion(5));
}
}
