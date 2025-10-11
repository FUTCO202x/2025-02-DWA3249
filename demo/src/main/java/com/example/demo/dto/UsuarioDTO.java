package com.example.demo.dto;

import com.example.demo.dto.UsuarioDTO;

public class UsuarioDTO {
    private String nombre;
    private String apellido;

    public UsuarioDTO(String nombre, String apellido) {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
