package EjerciciosJSON;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dni", "nombre", "edad", "curso"})
public class PersonaEjemplo {
	
	String dni;
	String nombre;
	int edad;
	String curso;
	
	
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
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	@Override
	public String toString() {
		return "PersonaEjemplo [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + ", curso=" + curso + "]";
	}
	

}
