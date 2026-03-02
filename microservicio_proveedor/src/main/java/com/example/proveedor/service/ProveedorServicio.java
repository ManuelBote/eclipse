package com.example.proveedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicio {

	@Autowired
	private ProveedorRepositorio repository;

	public List<Proveedor> findAll() {
		return repository.findAll();
	}

	public Optional<Proveedor> findById(int id) {
		return repository.findById(id);
	}

	public Proveedor save(Proveedor product) {
		return repository.save(product);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
