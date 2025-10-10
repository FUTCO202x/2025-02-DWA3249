package com.ejemplo.usuarios.dto;

public class NombreCompletoDTO {
    private String nombreCompleto;

    public NombreCompletoDTO(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
