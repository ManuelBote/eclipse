package OTO;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {
	@Id
	@Column(name = "dni")
	private String dni;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "edad")
	private int edad;

	@Column(name = "telefono")
	private int telefono;

	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	private Direccion direccion;

	public Persona() {
	}

	public Persona(String dni, String nombre, int edad, int telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
	}

	// Getters y setters...
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
	    return String.format("Persona{dni='%s', nombre='%s', edad=%d, telefono=%d, " +
	                        "direccion=%s}", 
	                        dni, nombre, edad, telefono, 
	                        direccion != null ? 
	                        String.format("Dirección(calle=%s, poblacion=%s, cp=%d)", 
	                                      direccion.getCalle(), direccion.getPoblacion(), direccion.getCp()) : "null");
	}

	
}