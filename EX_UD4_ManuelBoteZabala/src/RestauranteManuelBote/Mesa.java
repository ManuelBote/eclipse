package RestauranteManuelBote;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "mesa")
@Table(name = "mesa")
@NamedQuery(name = "totalMesas", query = "SELECT COUNT(*) FROM mesa WHERE camareroid = :idcamarero")
public class Mesa implements Serializable{
	
	@Id
	@Column(name = "idmesa")
	private int idMesa;

	@Column(name = "numeromesa")
	private int numeroMesa;
	
	@Column(name = "capacidadmesa")
	private int capacidadMesa;
	
	@ManyToOne
	@JoinColumn(name = "camareroid", referencedColumnName = "idcamarero")
	private Camarero camarero;
	
	@OneToOne(mappedBy = "mesa", cascade = CascadeType.ALL)
	private Reserva reserva;
	
	public Mesa() {
		
	}

	public Mesa(int idMesa, int numeroMesa, int capacidadMesa, Camarero camareroId) {
		this.idMesa = idMesa;
		this.numeroMesa = numeroMesa;
		this.capacidadMesa = capacidadMesa;
		this.camarero = camareroId;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getCapacidadMesa() {
		return capacidadMesa;
	}

	public void setCapacidadMesa(int capacidadMesa) {
		this.capacidadMesa = capacidadMesa;
	}

	public Camarero getCamareroId() {
		return camarero;
	}

	public void setCamareroId(Camarero camareroId) {
		this.camarero = camareroId;
	}
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}


	@Override
	public String toString() {
		return "Mesa [idMesa=" + idMesa + ", numeroMesa=" + numeroMesa + ", capacidadMesa=" + capacidadMesa
				+ ", camareroId=" + camarero + "]";
	}
	
	
	
	

}
