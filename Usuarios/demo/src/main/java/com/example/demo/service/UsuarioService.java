package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        // Usuarios quemados
        usuarios.add(new Usuario(1L, "Diana", "Diaz", "Diana@gmail.com"));
        usuarios.add(new Usuario(2L, "Ana", "Gomez", "ana@gmail.com"));
        usuarios.add(new Usuario(3L, "Luis", "Martinez", "luis@gmail.com"));
    }

    //Crear usuario
    public Usuario crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    // Obtener usuario por ID
    public Usuario obtenerPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
