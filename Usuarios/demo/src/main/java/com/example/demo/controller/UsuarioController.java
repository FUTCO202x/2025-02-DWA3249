package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear usuario
    @PostMapping("/usuarios")
    public UsuarioDTO crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.crearUsuario(usuario);
        String nombreCompleto = (nuevo.getNombre() + " " + nuevo.getApellido()).toUpperCase();
        return new UsuarioDTO(nombreCompleto);
    }

    // Obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    // Obtener usuario por ID
    @GetMapping("/usuarios/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    // Obtener nombre completo por los parámetros
    @GetMapping("/params")
    public UsuarioDTO obtenerPorParametros(@RequestParam String nombre, @RequestParam String apellido) {
        String nombreCompleto = (nombre + " " + apellido).toUpperCase();
        return new UsuarioDTO(nombreCompleto);
    }
}
