package Ejercicios;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "curso", "telefono"})
public class Alumno {
	

	String nombre, curso;
	int telefono;
	
	public Alumno() {
	}

	public Alumno(String nombre, String curso, int telefono) {
		this.nombre = nombre;
		this.curso = curso;
		this.telefono = telefono;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@XmlElement
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", curso=" + curso + ", telefono=" + telefono + "]";
	}
	
	
	
	

}
