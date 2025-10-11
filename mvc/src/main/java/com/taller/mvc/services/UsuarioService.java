package com.taller.mvc.services;

import java.util.List;

import com.taller.mvc.models.dto.NombreCompletoResponseDto;
import com.taller.mvc.models.dto.UsuarioCreateRequestDto;
import com.taller.mvc.models.dto.UsuarioCreateResponseDto;
import com.taller.mvc.models.dto.UsuarioResponseDto;

public interface UsuarioService {
    UsuarioCreateResponseDto createUsuario(UsuarioCreateRequestDto usuario);
    List<UsuarioResponseDto> getAllUsuarios();
    UsuarioResponseDto getUsuarioById(int id);
    NombreCompletoResponseDto getNombreApellido(String nombre, String apellido);
}
