package com.example.factura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos_factura")
public class ProductoFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long facturaId;
    private Long productoId;
    private Integer cantidad;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "factura_id", insertable = false, updatable = false)
    private Factura factura;

    public ProductoFactura() {}
    
	public ProductoFactura(Long facturaId, Long productoId, Integer cantidad, Double precio, Factura factura) {
		super();
		this.facturaId = facturaId;
		this.productoId = productoId;
		this.cantidad = cantidad;
		this.precio = precio;
		this.factura = factura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	
    
}