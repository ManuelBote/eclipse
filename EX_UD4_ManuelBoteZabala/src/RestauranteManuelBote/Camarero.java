package RestauranteManuelBote;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "camarero")
@Table(name = "camarero")
public class Camarero implements Serializable{
	
	@Id
	@Column(name = "idcamarero")
	private int idCamarero;
	
	@Column(name = "dnicamarero")
	private String dniCamarero;
	
	@Column(name = "nombrecamarero")
	private String nombreCamarero;
	
	@Column(name = "telefonocamarero")
	private int telefonoCamarero;
	
	@OneToMany(mappedBy = "camarero", cascade = CascadeType.ALL)
	private List<Mesa> mesas;
	
	public Camarero() {
	}

	public Camarero(int idCamarero, String dniCamarero, String nombreCamarero, int telefonoCamarero) {
		this.idCamarero = idCamarero;
		this.dniCamarero = dniCamarero;
		this.nombreCamarero = nombreCamarero;
		this.telefonoCamarero = telefonoCamarero;
	}



	public int getIdCamarero() {
		return idCamarero;
	}

	public void setIdCamarero(int idCamarero) {
		this.idCamarero = idCamarero;
	}

	public String getDniCamarero() {
		return dniCamarero;
	}

	public void setDniCamarero(String dniCamarero) {
		this.dniCamarero = dniCamarero;
	}

	public String getNombreCamarero() {
		return nombreCamarero;
	}

	public void setNombreCamarero(String nombreCamarero) {
		this.nombreCamarero = nombreCamarero;
	}

	public int getTelefonoCamarero() {
		return telefonoCamarero;
	}

	public void setTelefonoCamarero(int telefonoCamarero) {
		this.telefonoCamarero = telefonoCamarero;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public String toString() {
		return "Camarero [idcamarero=" + idCamarero + ", dniCamarero=" + dniCamarero + ", nombreCamarero="
				+ nombreCamarero + ", telefonoCamarero=" + telefonoCamarero + "]";
	}
	
	
	
	

}
