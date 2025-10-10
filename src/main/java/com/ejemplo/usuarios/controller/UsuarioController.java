package com.ejemplo.usuarios.controller;
import com.ejemplo.usuarios.dto.NombreCompletoDTO;
import com.ejemplo.usuarios.model.Usuario;
import com.ejemplo.usuarios.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public NombreCompletoDTO obtenerNombreCompleto(@RequestParam String nombre, @RequestParam String apellido) {
        String nombreCompleto = nombre + " " + apellido;
        return new NombreCompletoDTO(nombreCompleto);
    }
}
