package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import test.dataAccess.TestDataAccess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEventWithQuestion(String desc, Date d, String q, float qty) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEventWithQuestion(desc,d,q, qty);
			dbManagerTest.close();
			return o;

		}
	
	  // START - Additional tests

    @Test
    public void testRemoveEvent() {
        Event event = new Event();
        boolean result = removeEvent(event);
        assertTrue(result);
    }

    @Test
    public void testAddEventWithQuestion() {
        String description = "Event description";
        Date date = new Date();
        String question = "Question";
        float quantity = 10.0f;
        
        Event event = addEventWithQuestion(description, date, question, quantity);
        assertNotNull(event);
        assertEquals(description, event.getDescription());
        assertEquals(date, event.getDate());
        
        // Check if the event has a question
        assertNotNull(event.getQuestions());
        assertFalse(event.getQuestions().isEmpty());
        assertEquals(question, event.getQuestions().get(0).getQuestionText());
        assertEquals(quantity, event.getQuestions().get(0).getBetMinimum(), 0.001);
    }

}
