package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;
import com.example.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear usuario
    @PostMapping("/usuarios")
    public UsuarioDTO crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    // Obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    // Obtener usuario por ID
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return usuarioService.getUsuarioById(id);
    }

    // Recibir parámetros en la URL
    @GetMapping("/params")
    public Map<String, String> getParams(@RequestParam String nombre, @RequestParam String apellido) {
        return Map.of("nombreCompleto", nombre.toUpperCase() + " " + apellido.toUpperCase());
    }
}
