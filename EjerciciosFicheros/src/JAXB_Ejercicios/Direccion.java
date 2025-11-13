package JAXB_Ejercicios;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"calle", "numero", "localidad", "cp"})

public class Direccion {
	
    String calle, localidad;
    int numero, cp;
	
	public Direccion(String calle, String localidad, int numero, int cp) {
		this.calle = calle;
		this.localidad = localidad;
		this.numero = numero;
		this.cp = cp;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getCp() {
		return cp;
	}


	public void setCp(int cP) {
		this.cp = cp;
	}
	
}
