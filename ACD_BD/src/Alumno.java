
public class Alumno {

	int id;
	String nombre;
	String curso;
	int telefono;
	
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
	
	
	
	
	
}
