package EjerciciosJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {
	
	@JsonProperty("DNI")
	String dni;
	String nombre;
	int edad;
	
	public Persona() {
	}
	
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

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
	

}
