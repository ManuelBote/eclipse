package JAXB_Ejercicios;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nif", "nombre", "cargo"})

public class Trabajador {
	
    String nombre, cargo;
    int nif;
	
	
	public Trabajador(String nombre, String cargo, int nif) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.nif = nif;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public int getNif() {
		return nif;
	}
	
	public void setNif(int nif) {
		this.nif = nif;
	}
	
	
	
}
