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

public class CreateQuestionDAB {

	private CreateQuestionDAB createQuestionDAB;

@BeforeEach
public void setUp() {
    createQuestionDAB = new CreateQuestionDAB();
}

@Test
public void testCalculateMaxPoints() {
    assertEquals(10, createQuestionDAB.calculateMaxPoints(5, 2));
    assertEquals(0, createQuestionDAB.calculateMaxPoints(0, 0));
    assertEquals(15, createQuestionDAB.calculateMaxPoints(7, 3));
}

@Test
public void testGetQuestionById() {
    assertNotNull(createQuestionDAB.getQuestionById(1));
    assertNull(createQuestionDAB.getQuestionById(5));
}

@Test
public void testAddQuestion() {
    assertTrue(createQuestionDAB.addQuestion("Question 1", "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionDAB.addQuestion(null, "Option 1", "Option 2", 2, 10));
    assertFalse(createQuestionDAB.addQuestion("Question 2", null, "Option 2", 2, 10));
    assertFalse(createQuestionDAB.addQuestion("Question 3", "Option 1", null, 2, 10));
    assertFalse(createQuestionDAB.addQuestion("Question 4", "Option 1", "Option 2", -1, 10));
    assertFalse(createQuestionDAB.addQuestion("Question 5", "Option 1", "Option 2", 2, -1));
}

@Test
public void testDeleteQuestion() {
    assertTrue(createQuestionDAB.deleteQuestion(1));
    assertFalse(createQuestionDAB.deleteQuestion(5));
}
}

