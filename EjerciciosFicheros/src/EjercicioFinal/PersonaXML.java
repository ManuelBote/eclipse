package EjercicioFinal;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"dni", "telefono", "email"})
public class PersonaXML implements Serializable{
	
	
	String dni;
	int telefono;
	String email;
	
	public PersonaXML() {
	}

	public PersonaXML(String dni, int telefono, String email) {
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}

	@XmlElement
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@XmlElement
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonaXML [dni=" + dni + ", telefono=" + telefono + ", email=" + email + "]";
	}
	
	

	

	

}
