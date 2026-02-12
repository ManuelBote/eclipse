package OTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "idpersona", referencedColumnName = "dni")
	private Persona persona; 

	@Column(name = "calle")
	private String calle;

	@Column(name = "poblacion")
	private String poblacion;

	@Column(name = "cp")
	private int cp;

	public Direccion() {
	}

	public Direccion(Persona persona, String calle, String poblacion, int cp) {
		this.persona = persona;
		this.calle = calle;
		this.poblacion = poblacion;
		this.cp = cp;
	}

	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [persona=" + persona + ", calle=" + calle + ", poblacion=" + poblacion + ", cp=" + cp + "]";
	}

	
}