package Clase;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "alumno")
@Table(name = "alumno")
public class Alumno implements Serializable{


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "curso")
	private String curso;
	
	@Column(name = "telefono")
	private int telefono;
	
	public Alumno() {
	}

	public Alumno(int id, String nombre, String curso, int telefono) {
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.telefono = telefono;
	}
	
	public Alumno(String nombre, String curso, int telefono) {
		this.nombre = nombre;
		this.curso = curso;
		this.telefono = telefono;
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", curso=" + curso + ", telefono=" + telefono + "]";
	}
	
	
	
	
	
}
