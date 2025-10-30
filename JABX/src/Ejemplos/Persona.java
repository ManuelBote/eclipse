package Ejercicios;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "DNI", "nombre", "edad" })


	public class Persona {
		private String DNI, nombre;
		private int edad;

	// Constructor vacío
	public Persona() {
	}

	public Persona(String dNI, String nombre, int edad) {
		DNI = dNI;
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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
}
