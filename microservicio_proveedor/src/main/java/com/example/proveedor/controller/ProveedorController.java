package com.example.proveedor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.proveedor.model.Proveedor;
import com.example.proveedor.repository.ProveedorRepositorio;
import com.example.proveedor.service.ProveedorServicio;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {
	
	@Autowired
    private ProveedorServicio service;

    @GetMapping
    public List<Proveedor> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> ver(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor) {
        Proveedor nuevo = service.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

}
