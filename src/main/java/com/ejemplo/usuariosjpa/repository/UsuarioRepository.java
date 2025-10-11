package com.ejemplo.usuariosjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.usuariosjpa.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
