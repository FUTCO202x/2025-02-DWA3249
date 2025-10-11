package com.ejemplo.usuariosjpa.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.usuariosjpa.dto.NombreCompletoDTO;
import com.ejemplo.usuariosjpa.entity.Usuario;
import com.ejemplo.usuariosjpa.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }
    @GetMapping("/params")
    public NombreCompletoDTO obtenerNombreCompleto(
            @RequestParam String nombre,
            @RequestParam String apellido
    ) {
        String nombreCompleto = nombre + " " + apellido;
        return new NombreCompletoDTO(nombreCompleto);
    }
}
