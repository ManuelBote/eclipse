package Ejercicios;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Libreria {
	@XmlAttribute
	String nombre;
	@XmlAttribute
	String lugar;
	@XmlAttribute
	String cp;
	
	@XmlElementWrapper(name= "ListaLibro")
	@XmlElement(name = "Libro")
	ArrayList<Libro> libreria = new ArrayList<Libro>();

	
	public Libreria() {
	}
	
	
	public ArrayList<Libro> getLibro(){
		return libreria;
	}
	
	
	public void setLibro(ArrayList<Libro> libro) {
		libreria = libro;
	}
	
	public void mostrarLibros() {
		for (Libro l : libreria)
			System.out.println(l.toString());
	}
	
	public void aniadirLibro(Libro l) {
		this.libreria.add(l);
	}
	
	
	//Getter y setter de atributos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	
	
}
