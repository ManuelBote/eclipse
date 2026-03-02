package com.example.factura.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Factura {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long cliente;      // id del cliente
	    private LocalDate fecha;
	    private Double total;

	    // relación con productosFactura
	    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
	    private List<ProductoFactura> productos;
	    
	    
	    public Factura() {}


		public Factura(Long cliente, LocalDate fecha, Double total, List<ProductoFactura> productos) {
			super();
			this.cliente = cliente;
			this.fecha = fecha;
			this.total = total;
			this.productos = productos;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Long getCliente() {
			return cliente;
		}


		public void setCliente(Long cliente) {
			this.cliente = cliente;
		}


		public LocalDate getFecha() {
			return fecha;
		}


		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}


		public Double getTotal() {
			return total;
		}


		public void setTotal(Double total) {
			this.total = total;
		}


		public List<ProductoFactura> getProductos() {
			return productos;
		}


		public void setProductos(List<ProductoFactura> productos) {
			this.productos = productos;
		}

	   
	    
	    
}
