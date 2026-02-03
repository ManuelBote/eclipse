package com.example.persona.service;

import com.example.persona.model.Persona;
import com.example.persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository repository;

    public List<Persona> findAll() {
        return repository.findAll();
    }

    public Optional<Persona> findById(int id) {
        return repository.findById(id);
    }

    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
