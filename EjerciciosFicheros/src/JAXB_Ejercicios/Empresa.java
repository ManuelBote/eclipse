package JAXB_Ejercicios;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nie", "nombre", "direccion", "trabajadores"})
@XmlRootElement()
public class Empresa {
	
	int nie;
	String nombre;
	Direccion direccion;
	ArrayList<Trabajador>trabajadores = new ArrayList<>();
	
	public Empresa() {
	}
	
	public Empresa(int nie, String nombre, Direccion direccion, ArrayList<Trabajador> trabajadores) {
		this.nie = nie;
		this.nombre = nombre;
		this.direccion = direccion;
		this.trabajadores = trabajadores;
	}

	@XmlElement
	public int getNie() {
		return nie;
	}

	public void setNie(int nie) {
		this.nie = nie;
	}


	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	@XmlElement(name = "Trabajador")
	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	
	public void mostrarTrabajador() {
		for (Trabajador t : trabajadores)
			System.out.println(t.toString());
	}
	
	public void aniadirTrabajador(Trabajador t) {
		this.trabajadores.add(t);
	}
	
	

}
