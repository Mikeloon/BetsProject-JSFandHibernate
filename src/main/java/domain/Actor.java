package domain;

import java.util.Date;

import javax.persistence.*;
/**
 * Una clase abstracta para definir un actor en la aplicacion.
 * @author Mikel Lonbide & Ander Caro.
 * 05/03/2022
 */

@Entity
public abstract class Actor {
	
	@Id
	private String nombreUsuario; //Id del usuario.
	private String DNI;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaN; //fecha de nacimiento.
	private String pswd;
	private char sexo; //M, F, O.
	private String email;
	private String numTel;
	
	public Actor(String nombreUsuario, String DNI, String nombre, String apellido1, String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
		this.nombreUsuario= nombreUsuario;
		this.DNI= DNI;
		this.nombre= nombre;
		this.apellido1= apellido1;
		this.apellido2= apellido2;
		this.fechaN= fechaN;
		this.pswd= pswd;
		this.email= email;
		this.numTel= numTel;
	}
	
	//getters y setters.
	
	public Actor(String string, String i) {
		this.nombreUsuario = string;
		this.DNI = i;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
	public String getDNI() {
		return this.DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getPswd() {
		return pswd;
	}
	
	public void setPswd(String contrasena) {
		this.pswd = contrasena;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getFechaN() {
		return this.fechaN;
	}
	
	public String getNumTel() {
		return this.numTel;
	}
	
	public void setNumTel(String numTel) {
		this.numTel= numTel;
	}
	
	//fin getters y setters.
	
	/**
	 * Determina si este actor es un administrador.
	 * @return true si es administrador.
	 */
	public boolean isAdmin() {
		return (this instanceof Administrador);
	}
	
	/**
	 * Verifica que la contrase�a entrante coincide con la del usuario.
	 * @param pswd la contrase�a entrante.
	 * @return true si coincide.
	 */
	public boolean checkPswd(String pswd) {
		
		return this.getPswd().equals(pswd);
	}
	
	/**
	 * Devuelve la informaci�n del usuario en un string.
	 */
	public String toString() {
		return (this.getNombreUsuario()+": "+this.getNombre()+", "+this.getApellido1()+", "+this.getApellido2()+
				".\n\tDni: "+this.getDNI()+"\n\tFecha de nacimiento: "+this.getFechaN().toString()+"\n\tEmail: "+
				this.getEmail()+"\n\tN�mero de tel�fono: "+this.getNumTel()+"\n");
	}

}
