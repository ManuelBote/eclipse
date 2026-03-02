package com.example.factura.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.factura.model.Factura;
import com.example.factura.repository.FacturaRepositorio;
import com.example.factura.service.FacturaServicio;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaServicio service;

    @PostMapping
    public List<Factura> crearYListar(@RequestBody FacturaRequest request) {
        service.crearFactura(request);
        return service.listarTodas();
    }
}