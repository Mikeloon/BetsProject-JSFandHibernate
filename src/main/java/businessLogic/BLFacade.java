package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Actor;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.w3c.dom.events.EventException;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public int registrarUsuario(ArrayList<String> datos, Date fechaN, char sexo, boolean admin);
	
	@WebMethod public int crearEvento(String nombre, Date fecha, String descripcion, Actor admin) throws EventException;
	
	@WebMethod public boolean actorExistente (String nombreUsuario);
	
	@WebMethod public boolean existeLaPregunta (int id, Event evento);
	
	@WebMethod public int comprobarContrasena (String user, String pwd);
	
	@WebMethod public Vector<Question> obtenerPreguntasPorEvento (Event evento);
	
	@WebMethod public Actor obtenerActor (String user);
	
	@WebMethod public Vector<Event> obtenerEventosAdmin(String nAdmin);
	
	@WebMethod public Vector<Question> obtenerPreguntasAdmin(String nAdmin);
	
	@WebMethod public void borrarEvento(Event ev);

	@WebMethod public void borrarPregunta(Question p);
	
	@WebMethod public void close();
	
}
