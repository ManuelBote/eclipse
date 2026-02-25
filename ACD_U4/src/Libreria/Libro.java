package Libreria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
@NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn")
@NamedQuery(name = "Libro.totalLibrosEjemplares", query = "SELECT COUNT(l), SUM(l.numEjemplares) FROM Libro l")
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false)
    private int numEjemplares;

    @OneToMany(mappedBy = "libro", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Prestamo> prestamos = new ArrayList<>();

    public Libro() {}

    public Libro(String isbn, String titulo, int numEjemplares) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
    }

    // Getters y setters
    public Long getId() {
  		return id;
  	}

  	public void setId(Long id) {
  		this.id = id;
  	}

  	public String getIsbn() {
  		return isbn;
  	}

  	public void setIsbn(String isbn) {
  		this.isbn = isbn;
  	}

  	public String getTitulo() {
  		return titulo;
  	}

  	public void setTitulo(String titulo) {
  		this.titulo = titulo;
  	}

  	public int getNumEjemplares() {
  		return numEjemplares;
  	}

  	public void setNumEjemplares(int numEjemplares) {
  		this.numEjemplares = numEjemplares;
  	}

  	public List<Prestamo> getPrestamos() {
  		return prestamos;
  	}

  	public void setPrestamos(List<Prestamo> prestamos) {
  		this.prestamos = prestamos;
  	}
  	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return java.util.Objects.equals(isbn, libro.isbn);
    }

  

	@Override
    public int hashCode() { return java.util.Objects.hash(isbn); }
}