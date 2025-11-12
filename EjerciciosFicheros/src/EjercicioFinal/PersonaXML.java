package EjercicioFinal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"dni", "telefono", "email"})
public class PersonaXML {
	
	@XmlElement
	String dni;
	@XmlElement
	int telefono;
	@XmlElement
	String email;
	
	public PersonaXML() {
	}

	public PersonaXML(String dni, int telefono, String email) {
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

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
