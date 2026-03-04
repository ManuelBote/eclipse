package RestauranteManuelBote;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "reserva")
@Table(name = "reserva")
public class Reserva implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idreserva")
	private int idReserva;
	
	@Column(name = "nombrecliente")
	private String nombreCliente;
	
	@Column(name = "fechareserva")
	private String fechaReserva;
	
	@Column(name = "horareserva")
	private String horaReserva;
	
	@OneToOne
	@JoinColumn(name = "mesaid", referencedColumnName = "idmesa")
	private Mesa mesa;
	
	public Reserva() {
	}

	public Reserva(String nombreCliente, String fechaReserva, String horaReserva, Mesa mesaId) {
		this.nombreCliente = nombreCliente;
		this.fechaReserva = fechaReserva;
		this.horaReserva = horaReserva;
		this.mesa = mesaId;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public Mesa getMesaId() {
		return mesa;
	}

	public void setMesaId(Mesa mesaId) {
		this.mesa = mesaId;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", nombreCliente=" + nombreCliente + ", fechaReserva=" + fechaReserva
				+ ", horaReserva=" + horaReserva + ", mesaId=" + mesa + "]";
	}
	
	
	

}
