package com.ejemplo.usuariosjpa.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.usuariosjpa.entity.Usuario;
import com.ejemplo.usuariosjpa.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void initUsuarios() {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("Pablo", "Olmos", "pablo@gmail.com"));
            usuarioRepository.save(new Usuario("María", "Rodriguez", "maria@gmail.com"));
            usuarioRepository.save(new Usuario("David", "Torres", "david@gmail.com"));
        }
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setNombre(usuario.getNombre().toUpperCase());
        usuario.setApellido(usuario.getApellido().toUpperCase());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
