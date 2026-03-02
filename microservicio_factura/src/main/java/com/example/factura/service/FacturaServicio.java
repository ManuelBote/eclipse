package com.example.factura.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.factura.model.Factura;
import com.example.factura.model.ProductoFactura;
import com.example.factura.repository.FacturaRepositorio;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Factura crearFactura(FacturaRequest request) {

        // 1. Obtener cliente
        ClienteDTO cliente = restTemplate.getForObject(
                "http://localhost:8081/clientes/" + request.getClienteId(),
                ClienteDTO.class);

        // 2. Recorrer productos y obtener info de cada uno
        double total = 0.0;
        List<ProductoFactura> lineas = new ArrayList<>();

        for (ItemFacturaRequest item : request.getItems()) {
            ProductoDTO producto = restTemplate.getForObject(
                    "http://localhost:8082/productos/" + item.getProductoId(),
                    ProductoDTO.class);

            double subtotal = producto.getPrecio() * item.getCantidad();
            total += subtotal;

            ProductoFactura pf = new ProductoFactura();
            pf.setProductoId(producto.getId());
            pf.setCantidad(item.getCantidad());
            pf.setPrecio(producto.getPrecio());
            lineas.add(pf);
        }

        // 3. Crear entidad Factura y guardar
        Factura factura = new Factura();
        factura.setCliente(request.getClienteId());
        factura.setFecha(LocalDate.now());
        factura.setTotal(total);
        factura.setProductos(lineas);

        // relacionar las líneas con la factura si usas la asociación bidireccional
        lineas.forEach(p -> p.setFactura(factura));

        return facturaRepository.save(factura);
    }
}