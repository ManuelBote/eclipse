package SistemaAlumno;

public class Alumno {
	
	private int id_alumno;
    private String nombre;
    private String apellido;
    private String email;
    
    public Alumno() {
    }

	public Alumno(int id_alumno, String nombre, String apellido, String email) {
		this.id_alumno = id_alumno;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
	public Alumno(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public int getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Alumno [id_alumno=" + id_alumno + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}
    
	
    

}
