package businessLogic;
import java.util.ArrayList;


import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.w3c.dom.events.EventException;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccessInterface;
import domain.Question;
import domain.Actor;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		/*if (c.getDataBaseOpenMode().equals("initialize")) {
			
		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
			dbManager.initializeDB();
			dbManager.close();
			}
		*/
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.emptyDatabase();
			da.open();
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	//REGISTER ZONE

		/**
		 * 
		 * @param id
		 * @param DNI
		 * @param Nombre
		 * @param Apellido1
		 * @param Apellido2
		 * @param fechaN
		 * @param contrasena
		 * @param sexo
		 * @param email
		 * @param saldo
		 * @param admin :especifica si la bd tiene que crear un actor usuario o un actor admin (true: admin)
		 * @return 0: Todx correcto; 1: Fecha es incorrecta; 2: Saldo es negativo
		 */
		public int registrarUsuario(ArrayList<String> datos, Date fechaN, char sexo, boolean admin) {


			dbManager.open();

			Date fechaHoy = UtilDate.currentDate();
			Date fechaHace18 = UtilDate.fechaMayorEdad();

			if (dbManager.actorExistente(datos.get(0))) {

				dbManager.close();
				return 1; //El usuario que intenta anadir ya existe
			}
			else 

				if (fechaHoy.before(fechaN)) {
					dbManager.close();
					return 2; //Comprueba que la fecha es anterior al momento actual (Para que no se pueda poner que has nacido en 2025). Devuelve 2 si la fecha esta mal.
				}

				else 
					if (fechaN.before(fechaHace18))
						dbManager.registrarUsuario(datos, fechaN, sexo, admin);
					else 
						return 3;

			dbManager.close();
			return 0;
		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		/**
		 * Este metodo es para comprobar que un actor (usuario o administrador) ya existe.
		 * @param user (El usuario que la Intefaz pasa para comprobar si el usuario existe)
		 * @return True si existe el usuario, False si no.
		 */
		public boolean actorExistente (String nombreUsuario) {

			boolean existe=false;
			dbManager.open();
			existe = dbManager.actorExistente(nombreUsuario);
			dbManager.close();
			return existe;
		}
		
		
		/**
		 * Dado un usuario y una contresena comprueba que la contrasena dada y la asiganda al usuario coinciden
		 * 
		 * @param user El usuario del cual queremos combrobar la contrasena
		 * @param pwd La contrasena qie debemos comprobar
		 * @return 0: Si el nombre de usuario no esta registrado; 1: La pwd es correcta y es user; 2: La pwd es correcta y es admin; 3: contrase�a erronea
		 */
		public int comprobarContrasena (String user, String pwd) {

			dbManager.open();

			if (dbManager.actorExistente(user)) {

				if (dbManager.comprobarContrasena(user, pwd)) { //si el usuario existe, y la pwd es correcta, entramos a comprobar si es admin o user

					if (dbManager.esAdmin(user)) {
						dbManager.close();
						return 2; //2 si es admin
					}
					else {
						dbManager.close();
						return 1; //1 si es user
					}
				}
				else
					return 3; //si no coinciden nombre y contrase�a
			}
			dbManager.close();
			return 0; //0 si el nombre de usuario no esta registrado
		}

		/**
		 * Dado el parametro clave de usurario manda a la clase DataAccess buscar el usuario correspondiente
		 * 
		 * @param user Nombre del usuario que queremos obtener
		 * @return Null si el user no existe, el objeto Usuario al que corresponda ese nombre
		 */
		public Actor obtenerActor (String user) {

			Actor usuario = null;
			dbManager.open();
			if (dbManager.actorExistente(user)) { //Comprobar si el usuario existe
				usuario= dbManager.obtenerActor (user); //Pillar el user de la BD
			}
			dbManager.close();
			return usuario;
		}

	
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

}

