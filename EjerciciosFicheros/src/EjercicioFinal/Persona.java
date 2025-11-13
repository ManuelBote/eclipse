package EjercicioFinal;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class Persona implements Serializable {
	
	String dni;
	String nombre;
	int edad;
	int telefono;
	String email;
	
	public Persona() {
	}
	

	public Persona(String dni, String nombre, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}
	

	public Persona(String dni, int telefono, String email) {
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}


	public Persona(String dni, String nombre, int edad, int telefono, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.email = email;
	}
	
	
	@XmlElement
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@XmlElement
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + ", email="
				+ email + "]";
	}
	
	
	
	

}
