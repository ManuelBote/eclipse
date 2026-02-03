package com.example.persona.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    // Constructores
    public Persona() {}

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // GETTERS Y SETTERS EXPLÍCITOS (¡IMPORTANTE!)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
