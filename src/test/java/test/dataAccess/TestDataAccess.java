package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Event;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }

	 @BeforeEach
    public void setUp() {
        open();
    }
    
    @AfterEach
    public void tearDown() {
        close();
    }
    
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
