import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
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

    //sut: system under test
    static DataAccessInterface sut;
    static TestDataAccess testDA;

    private Event ev;

    @Before
    public void setUp() {
        sut = new DataAccess();
        testDA = new TestDataAccess();
    }

    @After
    public void tearDown() {
        if (ev != null) {
            testDA.open();
            testDA.removeEvent(ev);
            testDA.close();
        }
    }

    @Test
    //sut.createQuestion: The event has NOT one question with a queryText.
    public void test1() {
        try {

            //define parameters
            String eventText = "event1";
            String queryText = "query1";
            Float betMinimum = 2f;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date oneDate = sdf.parse("05/10/2022");

            //configure the state of the system (create object in the database)
            testDA.open();
            ev = testDA.addEventWithQuestion(eventText, oneDate, "query2", betMinimum);
            testDA.close();

            //invoke System Under Test (sut)
            Question q = sut.createQuestion(ev, queryText, betMinimum);

            //verify the results
            assertNotNull(q);
            assertEquals(q.getQuestion(), queryText);
            assertEquals(q.getBetMinimum(), betMinimum, 0);

            //verify the question is in the database
            testDA.open();
            boolean exist = testDA.existQuestion(ev, q);

            assertTrue(exist);
            testDA.close();

        } catch (QuestionAlreadyExist e) {
            // if the program goes to this point, fail
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    //sut.createQuestion: The event is null. The test should fail.
    public void test2() {
        try {

            //define parameters
            String queryText = "query1";
            Float betMinimum = 2f;

            //invoke System Under Test (sut)
            Question q = sut.createQuestion(null, queryText, betMinimum);

            //verify the results
            assertNull(q);

        } catch (QuestionAlreadyExist e) {
            // if the program goes to this point, fail
            fail();
        }
    }

    @Test
    //sut.createQuestion: The question is null. The test should fail.
    public void test3() {
        try {

            //define parameters
            String eventText = "event1";
            String queryText = null;
            Float betMinimum = 2f;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date oneDate = sdf.parse("05/10/2022");

            //configure the state of the system (create object in the database)
            testDA.open();
            ev = testDA.addEventWithQuestion(eventText, oneDate, "query2", betMinimum);
            testDA.close();

            //invoke System Under Test (sut)
            Question q = sut.createQuestion(ev, queryText, betMinimum);

            //verify the results
            assertNull(q);

            //verify the question is not in the database
            testDA.open();
            boolean exist = testDA.existQuestion(ev, q);

            assertFalse(exist);
            testDA.close();

        } catch (QuestionAlreadyExist e) {
            // if the program goes to this point, fail
            fail();
        } catch (ParseException e) {
            e.printStackTrace();
            fail();
        }
    }
}
