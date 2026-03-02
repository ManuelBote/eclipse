package com.example.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepositorio;

@Service
public class ClienteServicio {


    @Autowired
    private ClienteRepositorio repo;

    public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Optional<Cliente> findById(Long id) {
		return repo.findById(id);
	}

}
