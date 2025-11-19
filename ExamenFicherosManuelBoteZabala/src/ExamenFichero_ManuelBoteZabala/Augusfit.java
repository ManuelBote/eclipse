package ExamenFichero_ManuelBoteZabala;

import java.io.Serializable;

//@Author Manuel Bote Zabala
public class Augusfit implements Serializable{

	int id;
	String nombre;
	int pasosDiarios, caloriasQuemadas, horasSueno, ritmoCardiaco;
	String comentaio;
	
	public Augusfit() {
	}
	
	public Augusfit(int id, String nombre, int pasosDiarios, int caloriasQuemadas, int horasSueno, int ritmoCardiaco,
			String comentaio) {
		this.id = id;
		this.nombre = nombre;
		this.pasosDiarios = pasosDiarios;
		this.caloriasQuemadas = caloriasQuemadas;
		this.horasSueno = horasSueno;
		this.ritmoCardiaco = ritmoCardiaco;
		this.comentaio = comentaio;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getPasosDiarios() {
		return pasosDiarios;
	}


	public void setPasosDiarios(int pasosDiarios) {
		this.pasosDiarios = pasosDiarios;
	}


	public int getCaloriasQuemadas() {
		return caloriasQuemadas;
	}


	public void setCaloriasQuemadas(int caloriasQuemadas) {
		this.caloriasQuemadas = caloriasQuemadas;
	}


	public int getHorasSueno() {
		return horasSueno;
	}


	public void setHorasSueno(int horasSueno) {
		this.horasSueno = horasSueno;
	}


	public int getRitmoCardiaco() {
		return ritmoCardiaco;
	}


	public void setRitmoCardiaco(int ritmoCardiaco) {
		this.ritmoCardiaco = ritmoCardiaco;
	}


	public String getComentaio() {
		return comentaio;
	}


	public void setComentaio(String comentaio) {
		this.comentaio = comentaio;
	}


	@Override
	public String toString() {
		return "Augusfit [id=" + id + ", nombre=" + nombre + ", pasosDiarios=" + pasosDiarios + ", caloriasQuemadas="
				+ caloriasQuemadas + ", horasSueno=" + horasSueno + ", ritmoCardiaco=" + ritmoCardiaco + ", comentaio="
				+ comentaio + "]";
	}
	
	
	
	
	
	
	
}
