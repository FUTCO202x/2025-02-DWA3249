package com.taller.proyecto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller.proyecto.models.Estudent;

@Repository
public interface EstudentRepository extends JpaRepository<Estudent, Integer> {
    Optional<Estudent> findByNameIgnoreCaseAndLastNameIgnoreCase(String name, String lastName);
}
