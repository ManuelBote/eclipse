package ExamenFichero_ManuelBoteZabala;

import java.io.Serializable;

//@Author Manuel Bote Zabala
public class AugusFitJson implements Serializable{
	
	int id, pasosDiarios, caloriasQuemadas, horasSueno, ritmoCardiaco;
	
	public AugusFitJson() {
	}

	public AugusFitJson(int id, int pasosDiarios, int caloriasQuemadas, int horasSueno, int ritmoCardiaco) {
		this.id = id;
		this.pasosDiarios = pasosDiarios;
		this.caloriasQuemadas = caloriasQuemadas;
		this.horasSueno = horasSueno;
		this.ritmoCardiaco = ritmoCardiaco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	

}
