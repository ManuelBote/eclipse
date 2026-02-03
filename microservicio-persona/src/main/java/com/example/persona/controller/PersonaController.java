package com.example.persona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.persona.model.Persona;
import com.example.persona.service.PersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaService service;

    @GetMapping
    public List<Persona> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable int id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Persona create(@RequestBody Persona persona) {
        return service.save(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> update(@PathVariable int id, @RequestBody Persona persona) {
        if (service.findById(id).isPresent()) {
            persona.setId(id);
            return ResponseEntity.ok(service.save(persona));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
