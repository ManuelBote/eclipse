package SistemaAlumno;

public class Curso {

	private int id_curso;
   	private String nombre;
   	private int horas;
   	private String descripcion;
   	
   	public Curso() {
   	}

	public Curso(int id_curso, String nombre, int horas, String descripcion) {
		this.id_curso = id_curso;
		this.nombre = nombre;
		this.horas = horas;
		this.descripcion = descripcion;
	}
	
	public Curso(String nombre, int horas, String descripcion) {
		this.nombre = nombre;
		this.horas = horas;
		this.descripcion = descripcion;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Curso [id_curso=" + id_curso + ", nombre=" + nombre + ", horas=" + horas + ", descripcion="
				+ descripcion + "]";
	}
   	
   	

	
}
