package com.example.productos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepositorio;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	

    @Autowired
    private ProductoRepositorio service;

    // Obtener todo
    @GetMapping
    public List<Producto> getAll() {
        return service.findAll();
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable int id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404 si no existe
    }

    // Agregar
    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody Producto product) {
    	Producto created = service.save(product);
        return ResponseEntity.ok(created); // 200 con el producto en JSON
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable int id,
                                          @Valid @RequestBody Producto product) {
        if (service.findById(id).isPresent()) {
            product.setId(id);
            return ResponseEntity.ok(service.save(product));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Manejo de errores de validación → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Datos incorrectos");
    }

}
