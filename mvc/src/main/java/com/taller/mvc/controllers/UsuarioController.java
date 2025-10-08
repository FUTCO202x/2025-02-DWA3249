package com.taller.mvc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller.mvc.models.dto.NombreCompletoResponseDto;
import com.taller.mvc.models.dto.UsuarioCreateRequestDto;
import com.taller.mvc.models.dto.UsuarioCreateResponseDto;
import com.taller.mvc.models.dto.UsuarioResponseDto;
import com.taller.mvc.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto getUsuarioById(@PathVariable int id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping()
    public UsuarioCreateResponseDto createUsuario(@RequestBody UsuarioCreateRequestDto usuario) {
        return usuarioService.createUsuario(usuario);
    }
    
    @GetMapping("/todos")
    public List<UsuarioResponseDto> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/nombre-completo")
    public NombreCompletoResponseDto getNombreCompleto(@RequestParam String nombre, @RequestParam String apellido) {
        return usuarioService.getNombreApellido(nombre, apellido);
    }

}
