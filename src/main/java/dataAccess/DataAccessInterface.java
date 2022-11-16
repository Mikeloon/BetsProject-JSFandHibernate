package dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Actor;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

public interface DataAccessInterface {

		
	/**
	 * This method opens the database
	 */
	void open();
	
	/**
	 * This method closes the database
	 */
	void close();

	
	/**
	 * This method removes all the elements of the database
	 */
	void emptyDatabase();
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	void initializeDB();

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist;

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	Vector<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	Vector<Date> getEventsMonth(Date date);

	
	/**
	 * This method checks if the question has been previously added to the event 
	 * 
	 * @param event the event
	 * @param question the question to check  
	 * @return true if the event contains this the questions, false in other case
	 */
	boolean existQuestion(Event event, String question);

	boolean actorExistente(String string);

	void registrarUsuario(ArrayList<String> datos, Date fechaN, char sexo, boolean admin);

	boolean existeEvento(String nombre, Date fecha);

	void crearEvento(String nombre, Date fecha, String descripcion);

	boolean existePregunta(int id, Event evento);

	boolean comprobarContrasena(String user, String pwd);

	boolean esAdmin(String user);

	Vector<Question> obtenerPreguntasPorEvento(Event evento);

	Actor obtenerActor(String user);

	Vector<Event> obtenerEventosAdmin(String nAdmin);

	Vector<Question> obtenerPreguntasAdmin(String nAdmin);

	void borrarEvento(Event ev);

	void borrarPregunta(Question p);

	

}