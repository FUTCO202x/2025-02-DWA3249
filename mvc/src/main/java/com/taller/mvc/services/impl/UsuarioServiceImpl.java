package com.taller.mvc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taller.mvc.models.dto.NombreCompletoResponseDto;
import com.taller.mvc.models.dto.UsuarioCreateRequestDto;
import com.taller.mvc.models.dto.UsuarioCreateResponseDto;
import com.taller.mvc.models.dto.UsuarioResponseDto;
import com.taller.mvc.models.entities.Usuario;
import com.taller.mvc.repositories.UsuarioRepository;
import com.taller.mvc.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDto getUsuarioById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return new UsuarioResponseDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
        }
        return null;
    }

    @Override
    public NombreCompletoResponseDto getNombreApellido(String nombre, String apellido) {
        Usuario usuario = usuarioRepository.findByNombreAndApellido(nombre, apellido);
        if (usuario != null) {
            return new NombreCompletoResponseDto(usuario.getNombre() +" "+ usuario.getApellido());
        }
        return null;
    }

    @Override
    public UsuarioCreateResponseDto createUsuario(UsuarioCreateRequestDto usuario) {

        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNombre(usuario.nombre());
        usuarioEntity.setApellido(usuario.apellido());
        usuarioEntity.setEmail(usuario.email());


        Usuario usuarioEntitySaved = usuarioRepository.save(usuarioEntity);
        return new UsuarioCreateResponseDto(usuarioEntitySaved.getNombre().toUpperCase(), usuarioEntitySaved.getApellido().toUpperCase());
    }

    @Override
    public List<UsuarioResponseDto> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDto> usuarioResponseDtos = new ArrayList<>();
        for(Usuario usuario : usuarios) {
            UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
            usuarioResponseDtos.add(usuarioResponseDto);
        }
        return usuarioResponseDtos;
    }

}
