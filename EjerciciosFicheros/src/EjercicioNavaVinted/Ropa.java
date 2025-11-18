package EjercicioNavaVinted;

import java.io.Serializable;

//@Author Manuel Bote Zabala
public class Ropa implements Serializable{

	int id;
	String nombre, categoria, talla, color, material;
	int stock, precio, coste;
	String estado;
	int descuento;
	
	public Ropa() {
	}

	public Ropa(int id, String nombre, String categoria, String talla, String color, String material, int stock,
			int precio, int coste, String estado, int descuento) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.talla = talla;
		this.color = color;
		this.material = material;
		this.stock = stock;
		this.precio = precio;
		this.coste = coste;
		this.estado = estado;
		this.descuento = descuento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	@Override
	public String toString() {
		return "Ropa [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", talla=" + talla + ", color="
				+ color + ", material=" + material + ", stock=" + stock + ", precio=" + precio + ", coste=" + coste
				+ ", estado=" + estado + ", descuento=" + descuento + "]";
	}

	
	
	
	
	
	
	
	
	
}
