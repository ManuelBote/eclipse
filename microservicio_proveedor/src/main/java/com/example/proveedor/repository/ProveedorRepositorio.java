package com.example.proveedor.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proveedor.model.Proveedor;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {
}