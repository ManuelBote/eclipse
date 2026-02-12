package OTM;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column()
	private String nombre;

	@OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alumno> alumnos = new ArrayList<>();

	// Constructor vacío
	public Profesor() {
	}

	public Profesor(String nombre) {
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

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	// ✅ Método para añadir alumnos (sincroniza ambos lados)
	public void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
		alumno.setProfesor(this);
	}

	@Override
	public String toString() {
		return "Profesor{id=" + id + ", nombre=" + nombre + "}";
	}
}