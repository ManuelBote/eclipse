package com.example.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Producto {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @NotBlank
	    @Column(name = "name", length = 100, nullable = false)
	    private String name;

	    @NotBlank
	    @Column(name = "description", length = 255, nullable = false)
	    private String description;

	    @NotNull
	    @Column(name = "price", nullable = false)
	    private Double price;

	    @NotNull
	    @Column(name = "stock", nullable = false)
	    private Integer stock;

	    public Producto() {}

	    public Producto(String name, String description, Double price, Integer stock) {
	        this.name = name;
	        this.description = description;
	        this.price = price;
	        this.stock = stock;
	    }

	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public Double getPrice() { return price; }
	    public void setPrice(Double price) { this.price = price; }

	    public Integer getStock() { return stock; }
	    public void setStock(Integer stock) { this.stock = stock; }

}
