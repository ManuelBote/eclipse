package Ejercicios;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"NIF", "Nombre", "Cargo"})

public class Trabajador {

	String Nombre, Cargo;
	int NIF;
	
	
	public Trabajador(String nombre, String cargo, int nIF) {
		Nombre = nombre;
		Cargo = cargo;
		NIF = nIF;
	}
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getCargo() {
		return Cargo;
	}
	
	public void setCargo(String cargo) {
		Cargo = cargo;
	}
	
	public int getNIF() {
		return NIF;
	}
	
	public void setNIF(int nIF) {
		NIF = nIF;
	}
	
	
	
}
