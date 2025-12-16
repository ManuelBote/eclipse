package Ejercicios;

public class Producto {

	int id;
	String nombre;
	int id_cat, id_talla, id_color, id_mat, stock;
	double precio, costo;
	String estado;
	int descuento;
	
	public Producto() {
	}

	public Producto(int id, String nombre, int id_cat, int id_talla, int id_color, int id_mat, int stock, double precio,
			double costo, String estado, int descuento) {
		this.id = id;
		this.nombre = nombre;
		this.id_cat = id_cat;
		this.id_talla = id_talla;
		this.id_color = id_color;
		this.id_mat = id_mat;
		this.stock = stock;
		this.precio = precio;
		this.costo = costo;
		this.estado = estado;
		this.descuento = descuento;
	}

	public Producto(String nombre, int id_cat, int id_talla, int id_color, int id_mat, int stock, double precio,
			double costo, String estado, int descuento) {
		this.nombre = nombre;
		this.id_cat = id_cat;
		this.id_talla = id_talla;
		this.id_color = id_color;
		this.id_mat = id_mat;
		this.stock = stock;
		this.precio = precio;
		this.costo = costo;
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

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public int getId_talla() {
		return id_talla;
	}

	public void setId_talla(int id_talla) {
		this.id_talla = id_talla;
	}

	public int getId_color() {
		return id_color;
	}

	public void setId_color(int id_color) {
		this.id_color = id_color;
	}

	public int getId_mat() {
		return id_mat;
	}

	public void setId_mat(int id_mat) {
		this.id_mat = id_mat;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
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
	
	
	
	
	
	
	
}
