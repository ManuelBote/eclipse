package com.example.factura.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.factura.model.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Integer> {
}