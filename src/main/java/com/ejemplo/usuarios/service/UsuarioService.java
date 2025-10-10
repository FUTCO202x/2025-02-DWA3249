package com.ejemplo.usuarios.service;

import com.ejemplo.usuarios.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();
    public UsuarioService() {
        usuarios.add(new Usuario(1L, "Juan", "Padilla", "juan@gmail.com"));
        usuarios.add(new Usuario(2L, "María", "Camila", "maria@gmail.com"));
        usuarios.add(new Usuario(3L, "Carlos", "Valdelamar", "carlos@gmail.com"));
    }
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setId((long) (usuarios.size() + 1));
        usuario.setNombre(usuario.getNombre().toUpperCase());
        usuario.setApellido(usuario.getApellido().toUpperCase());
        usuarios.add(usuario);
        return usuario;
    }
    public List<Usuario> obtenerTodos() {
        return usuarios;
    }
    public Usuario obtenerPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
