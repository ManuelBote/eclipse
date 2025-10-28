package Ejercicios2Parte;

//@author Manuel Bote Zabala

public class Alumno {
	
	private int numEx;
	private String nombre;
	private Double nota;
	
	
	public Alumno(int numEx, String nombre, Double nota) {
		this.numEx = numEx;
		this.nombre = nombre;
		this.nota = nota;
	}


	public int getNumEx() {
		return numEx;
	}


	public void setNumEx(int numEx) {
		this.numEx = numEx;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getNota() {
		return nota;
	}


	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
	
	
	
}
