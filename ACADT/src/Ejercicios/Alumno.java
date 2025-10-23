package Ejercicios;

import java.util.ArrayList;
import java.util.Arrays;

public class Alumno {

	/*
	 * Implementaremos la clase Alumno con una serie de atributos y métodos.
	 * Atributos:  Expediente, ocho dígitos.  Nombre, un texto de 20 caracteres. 
	 * Apellidos una texto de 40 caracteres.  Notas, estructura de almacenamiento
	 * de notas numéricas con decimales. Esta estructura por lo tanto tendrá datos
	 * donde puede haber duplicados, el orden no es importante y su función
	 * principal no es de búsquedas Los métodos de la clase Alumno serán los métodos
	 * set y get para cada atributo y el método toString. Además:  Un método
	 * añadirNota que recibe como parámetro un número con decimal, el método lo debe
	 * añadir a la estructura de almacenamiento notas.  El método calcularNota que
	 * hace la media de todas las notas guardadas en la estructura de almacenamiento
	 * notas. Para ello sumamos todas las notas y las dividiremos por el número
	 * total de notas introducidas.
	 */

	int expediente;
	String nombre;
	String apellido;
	ArrayList<Double> notas;
	
	
	
	public Alumno() {
	}

	public Alumno(int expediente, String nombre, String apellido) {
		this.expediente = expediente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.notas = new ArrayList<Double>();
		
	}

	public int getExpediente() {
		return expediente;
	}

	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ArrayList<Double> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Double> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "Alumno [expediente=" + expediente + ", nombre=" + nombre + ", apellido=" + apellido + ", notas=" + notas
				+ "]";
	}	

	public void anadirNota(double num) {
		this.notas.add(num);
	}
	
	public double calcularNota() {
		double total = 0;
		for (double i : this.notas) {
			total += i;
		}
		double media = total/this.notas.size();
		return media;
	}

	

}
