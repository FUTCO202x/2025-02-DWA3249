package com.taller.proyecto.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.taller.proyecto.models.Estudent;
import com.taller.proyecto.models.DTO.EstudentResponseCreateDTO;

public interface InterfaceEstudent {
    EstudentResponseCreateDTO createEstudent(Estudent estudent);
    List<Estudent> getAllEstudents();
    Optional<Estudent> getEstudentById(int id);
    Optional<Estudent> getEstudentByNameAndLastName(String name, String lastName);
}
