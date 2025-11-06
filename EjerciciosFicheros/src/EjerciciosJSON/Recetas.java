package EjerciciosJSON;

import java.util.List;


public class Recetas {

	private String nombre;
	private String tipo;
	private Origen origen;
	private List<Ingredientes> ingredientes;
	
	public Recetas() {
	}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public List<Ingredientes> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "Recetas [nombre=" + nombre + ", tipo=" + tipo + ", origen=" + origen + ", ingredientes=" + ingredientes + "]";
	}

	
}
