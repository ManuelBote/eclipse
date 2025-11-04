package Ejercicios;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"Calle", "Numero", "Localidad", "CP"})

public class Direccion {
	
	String Calle, Localidad;
	int Numero, CP;
	
	
	public Direccion(String calle, String localidad, int numero, int cP) {
		Calle = calle;
		Localidad = localidad;
		Numero = numero;
		CP = cP;
	}


	public String getCalle() {
		return Calle;
	}


	public void setCalle(String calle) {
		Calle = calle;
	}


	public String getLocalidad() {
		return Localidad;
	}


	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}


	public int getNumero() {
		return Numero;
	}


	public void setNumero(int numero) {
		Numero = numero;
	}


	public int getCP() {
		return CP;
	}


	public void setCP(int cP) {
		CP = cP;
	}
	
}
