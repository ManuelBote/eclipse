package Ejercicios;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
public class Empresa {
	
	int NIE;
	String Nombre;
	Direccion Direccion;
	ArrayList<Trabajador>Trabajadores = new ArrayList<>();
	
	public Empresa() {
	}
	
	public Empresa(int nIE, String nombre, Ejercicios.Direccion direccion, ArrayList<Trabajador> trabajadores) {
		NIE = nIE;
		Nombre = nombre;
		Direccion = direccion;
		Trabajadores = trabajadores;
	}

	@XmlElement
	public int getNIE() {
		return NIE;
	}

	public void setNIE(int nIE) {
		NIE = nIE;
	}


	@XmlElement
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	@XmlElement
	public Direccion getDireccion() {
		return Direccion;
	}

	public void setDireccion(Direccion direccion) {
		Direccion = direccion;
	}
	
	@XmlElement(name = "Trabajador")
	public ArrayList<Trabajador> getTrabajadores() {
		return Trabajadores;
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		Trabajadores = trabajadores;
	}
	
	public void mostrarTrabajador() {
		for (Trabajador t : Trabajadores)
			System.out.println(t.toString());
	}
	
	public void aniadirTrabajador(Trabajador t) {
		this.Trabajadores.add(t);
	}
	
	

}
