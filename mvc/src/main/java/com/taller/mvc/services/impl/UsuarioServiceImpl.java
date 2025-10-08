package com.taller.mvc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taller.mvc.models.dto.NombreCompletoResponseDto;
import com.taller.mvc.models.dto.UsuarioCreateRequestDto;
import com.taller.mvc.models.dto.UsuarioCreateResponseDto;
import com.taller.mvc.models.dto.UsuarioResponseDto;
import com.taller.mvc.models.dto.UsuarioResponseNombreApellido;
import com.taller.mvc.models.entities.Usuario;
import com.taller.mvc.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioServiceImpl() {
        this.usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Juan", "Perez", "juan.perez@example.com"));
        usuarios.add(new Usuario(2, "Maria", "Gomez", "maria.gomez@example.com"));
        usuarios.add(new Usuario(3, "Pedro", "Lopez", "pedro.lopez@example.com"));
    }

    @Override
    public UsuarioResponseDto getUsuarioById(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return new UsuarioResponseDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
            }
        }
        return null;
    }

    @Override
    public NombreCompletoResponseDto getNombreApellido(String nombre, String apellido) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre) && usuario.getApellido().equalsIgnoreCase(apellido)) {
                return new NombreCompletoResponseDto(usuario.getNombre() + " " + usuario.getApellido());
            }
        }
        return null;
    }

    @Override
    public UsuarioCreateResponseDto createUsuario(UsuarioCreateRequestDto usuario) {
        int nuevoId = usuarios.size() + 1;
        for (Usuario u : usuarios) {
            if (u.getId() >= nuevoId) {
                nuevoId = u.getId() + 1;
            }
        }
        
        Usuario nuevoUsuario = new Usuario(nuevoId, usuario.nombre(), usuario.apellido(), usuario.email());
        usuarios.add(nuevoUsuario);

        return new UsuarioCreateResponseDto(usuario.nombre().toUpperCase(), usuario.apellido().toUpperCase());
    }

    @Override
    public List<UsuarioResponseDto> getAllUsuarios() {
        List<UsuarioResponseDto> usuariosResponse = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosResponse.add(new UsuarioResponseDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail()));
        }
        return usuariosResponse;
    }

}
