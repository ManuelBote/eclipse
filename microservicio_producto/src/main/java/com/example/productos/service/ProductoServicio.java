package com.example.productos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

	@Autowired
	private ProductoRepositorio repository;

	public List<Producto> findAll() {
		return repository.findAll();
	}

	public Optional<Producto> findById(int id) {
		return repository.findById(id);
	}

	public Producto save(Producto product) {
		return repository.save(product);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
