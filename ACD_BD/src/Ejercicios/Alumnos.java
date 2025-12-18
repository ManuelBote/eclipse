package Ejercicios;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "alumnos")
public class Alumnos {

	ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
	
	public Alumnos() {
	}

	@XmlElement(name = "alumno")
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	
	
	
	
}
