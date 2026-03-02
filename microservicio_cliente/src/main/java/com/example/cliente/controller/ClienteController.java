package com.example.cliente.controller;

import com.example.cliente.model.Cliente;
import com.example.cliente.service.ClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio service;

    @GetMapping
    public List<Cliente> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ver(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

