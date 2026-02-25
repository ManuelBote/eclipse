package Libreria;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "prestamo")
@NamedQuery(name = "Prestamo.pendientesTotal", query = "SELECT COUNT(p) FROM Prestamo p WHERE p.fechaDevolucion IS NULL")
@NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id")
@NamedQuery(name = "Prestamo.pendientesLibro", query = "SELECT COUNT(p) FROM Prestamo p WHERE p.libro.id = :libroId AND p.fechaDevolucion IS NULL")
@NamedQuery(name = "Prestamo.pendientesSocio", query = "SELECT COUNT(p) FROM Prestamo p WHERE p.socio.id = :socioId AND p.fechaDevolucion IS NULL")
public class Prestamo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @Column(nullable = false)
    private Date fechaPrestamo;

    private Date fechaDevolucion;

    public Prestamo() {}

    public Prestamo(Socio socio, Libro libro) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = new Date(System.currentTimeMillis());
        this.fechaDevolucion = null;
    }
    
    // Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}



}