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
	    private ProveedorRepositorio repo;

	    public List<Proveedor> findAll() { return repo.findAll(); }

	    public Optional<Proveedor> findById(Long id) { return repo.findById(id); }

	    public Proveedor save(Proveedor p) { return repo.save(p); }

}
