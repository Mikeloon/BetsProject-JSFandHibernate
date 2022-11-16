package domain;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
/**
 * Informacion de un administrador de la aplicacion.
 * @author Mikel Lonbide & Ander Caro.
 * 05/03/2022
 */

@Entity
public class Administrador extends Actor{

	@OneToMany
	private ArrayList<Question> preguntas; //preguntas formuladas por el administrador.
	@OneToMany
	private ArrayList<Event> eventos; //eventos creados por el administrador.
	
	
	public Administrador(String nombreUsuario, 
			String DNI, String nombre, String apellido1,
			String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
	
		super(nombreUsuario,  DNI,  nombre,  apellido1,  apellido2,  fechaN,  pswd,  Sexo,  email,  numTel);	
		this.eventos = new ArrayList<Event>();
		this.preguntas = new ArrayList<Question>();	
	}
	
	public ArrayList<Question> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(ArrayList<Question> preguntas) {
		this.preguntas = preguntas;
	}



	public ArrayList<Event> getEventos() {
		return eventos;
	}



	public void setEventos(ArrayList<Event> eventos) {
		this.eventos = eventos;
	}
	
	//fin getters y setters.

	/**
	 * Devuelve la pregunta que coincida con el enunciado de entrada.
	 * @param enunciado el enunciado de la pregunta a buscar.
	 * @return la pregunta con el mismo enunciado.
	 */
	public Question buscarPreguntaPorEnunciado(int id) {
		for(Question p: preguntas) {
			if(p.getQuestionNumber() == id) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * A�ade una pregunta a la lista de preguntas creadas por el administrador..
	 * @param pregunta la pregunta a a�adir.
	 */
	public void anadirPregunta(Question pregunta) {
		preguntas.add(pregunta);
	}
	
	/**
	 * Elimina la pregunta de entrada.
	 * @param pregunta la pregunta a eliminar.
	 */
	public void eliminarPregunta(Question pregunta) {
		preguntas.remove(pregunta);
	}
	
	/**
	 * Devuelve el evento con el nombre que se introduce.
	 * @param nombre el nombre del evento a buscar.
	 * @return el evento con el mismo nombre.
	 */
	public Event buscarEventoPorId(int number) {
		for(Event e: eventos) {
			if(e.getEventNumber() == number) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * A�ade un evento a la lista de eventos creados por el administrador.
	 * @param evento el evento a a�adir.
	 */
	public void anadirEvento(Event evento) {
		this.eventos.add(evento);
	}
	
	
}
