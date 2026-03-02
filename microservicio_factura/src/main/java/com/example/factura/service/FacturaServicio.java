package com.example.factura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.factura.model.Factura;
import com.example.factura.repository.FacturaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServicio {

	@Autowired
	private FacturaRepositorio repository;

	public List<Factura> findAll() {
		return repository.findAll();
	}

	public Optional<Factura> findById(int id) {
		return repository.findById(id);
	}

	public Factura save(Factura product) {
		return repository.save(product);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
