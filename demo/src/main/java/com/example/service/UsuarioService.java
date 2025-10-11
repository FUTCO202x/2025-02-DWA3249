package com.example.service;

import com.example.demo.model.Usuario;
import com.example.demo.dto.UsuarioDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1, "Carlos", "Perez", "carlos@mail.com"));
        usuarios.add(new Usuario(2, "Maria", "Lopez", "maria@mail.com"));
        usuarios.add(new Usuario(3, "Juan", "Gomez", "juan@mail.com"));
    }

    public UsuarioDTO crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return new UsuarioDTO(usuario.getNombre(), usuario.getApellido());
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioById(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
