package Ejercicios;

public class Persona {
	
	public String nombre;
	public String apellidos;
	public String dni;
	public int edad;
	public boolean estado_civil;
	
	public Persona() {
	}
	
	public Persona(String nombre, String apellidos, String dni, int edad, boolean estado_civil) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.estado_civil = estado_civil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(boolean estado_civil) {
		this.estado_civil = estado_civil;
	}

	public void mostrar() {
		System.out.println("Nombre: " + this.nombre);
		System.out.println("Apellidos: " + this.apellidos);
		System.out.println("DNI: " + this.dni);
		System.out.println("Edad: " + this.edad);
		if(this.estado_civil == true) {
			System.out.println("Estado civil: Casad@");
			
		}else {
			System.out.println("Estado civil: Solter@");

		}
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", edad=" + edad
				+ ", estado_civil=" + estado_civil + "]";
	}
	
	
	
	

}
