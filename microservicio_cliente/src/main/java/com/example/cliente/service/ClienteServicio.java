package com.example.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

	@Autowired
	private ClienteRepositorio repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Optional<Cliente> findById(int id) {
		return repository.findById(id);
	}

	public Cliente save(Cliente product) {
		return repository.save(product);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
