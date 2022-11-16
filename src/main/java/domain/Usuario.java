package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

/**
 * Informaci�n de un usuario de la aplicaci�n.
 * @author Markel Barrena.
 * 23/02/2022
 */

@Entity
public class Usuario extends Actor{
	
	private double saldo; //unidades monetarias ingresadas en la aplicaci�n.		
	private ArrayList<String> antiguasContrasenas;
	private ArrayList<String> antiguosCorreosElectronicos;
	
	public Usuario(String nombreUsuario, String DNI, String nombre, String apellido1, String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
		
		super(nombreUsuario,  DNI,  nombre,  apellido1,  apellido2,  fechaN,  pswd,  Sexo,  email,  numTel);
		this.saldo = 0;
		this.antiguasContrasenas = new ArrayList<String>();
		this.antiguosCorreosElectronicos = new ArrayList<String>();
	}

	public Usuario(String string) {
		super(string,"12345678N");
		this.antiguasContrasenas = new ArrayList<String>();
		this.antiguosCorreosElectronicos = new ArrayList<String>();
	}

	public Usuario(String string, String string2) {
		super(string,string2);
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo= saldo;
	}
		
	/**
	 * Actualiza el saldo sum�ndole la cantidad de entrada.
	 * @param cantidad la cantidad que se le suma (negativa si se va a sustraer).
	 */
	public void actualizarSaldo(double cantidad) {
		
		this.saldo = this.saldo + cantidad;
	}
	
	/**
	 * Devuelve la informaci�n del usuario en un string.
	 */
	public String toString() {
		return (this.getNombreUsuario()+": "+this.getNombre()+", "+this.getApellido1()+", "+this.getApellido2()+
				".\n\tDni: "+this.getDNI()+"\n\tFecha de nacimiento: "+this.getFechaN().toString()+"\n\tEmail: "+
				this.getEmail()+"\n\tN�mero de tel�fono: "+this.getNumTel()+"\n\tSaldo: "+this.saldo+"\n");
	}
	
	public ArrayList<String> getContrasenasAntiguas() {
		return this.antiguasContrasenas;
	}
	
	public ArrayList<String> getCorreosAntiguos() {
		return this.antiguosCorreosElectronicos;
	}

	public boolean cambiarContrasena(String nuevaContrasena) {
		
		for(String c : this.antiguasContrasenas)
			if (c.equals(nuevaContrasena))
				return false;
		
		if (this.getPswd().equals(nuevaContrasena))
			return false;
		
		this.antiguasContrasenas.add(this.getPswd());
		this.setPswd(nuevaContrasena);
		return true;
	}
	
	public boolean cambiarCorreoElectronico (String nuevoCorreoE) {
		
		for(String c : this.antiguosCorreosElectronicos)
			if (c.equals(nuevoCorreoE))
				return false;
		
		if (this.getEmail().equals(nuevoCorreoE))
			return false;
		
		this.antiguosCorreosElectronicos.add(this.getEmail());
		this.setEmail(nuevoCorreoE);
		return true;
	}
}

