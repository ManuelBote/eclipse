package OTM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	// ✅ RELACIÓN ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;

	// Constructores
	public Alumno() {
	}

	public Alumno(String nombre) {
		this.nombre = nombre;
	}

	// Getters y setters
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

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Alumno{id=" + id + ", nombre=" + nombre + ", profesor=" + profesor.getNombre() + "}";
	}
}
