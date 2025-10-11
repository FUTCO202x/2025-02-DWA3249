package com.taller.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taller.proyecto.models.Estudent;
import com.taller.proyecto.models.DTO.EstudentResponseCreateDTO;
import com.taller.proyecto.repositories.EstudentRepository;
import com.taller.proyecto.services.interfaces.InterfaceEstudent;

@Service
public class EstudentServices implements InterfaceEstudent {
    
    private final EstudentRepository estudentRepository;

    public EstudentServices(EstudentRepository estudentRepository) {
        this.estudentRepository = estudentRepository;
    }

    @Override
    public EstudentResponseCreateDTO createEstudent(Estudent estudent) {
        if (estudent == null) return null;

        String name = estudent.getName() == null ? "" : estudent.getName().trim().toUpperCase();
        String lastName = estudent.getLastName() == null ? "" : estudent.getLastName().trim().toUpperCase();
        
        estudent.setName(name);
        estudent.setLastName(lastName);

        Estudent saved = estudentRepository.save(estudent);

        return new EstudentResponseCreateDTO(saved.getName(), saved.getLastName());
    }

    @Override
    public List<Estudent> getAllEstudents() {
        return estudentRepository.findAll();
    }

    @Override
    public Optional<Estudent> getEstudentById(int id) {
        return estudentRepository.findById(id);
    }

    @Override
    public Optional<Estudent> getEstudentByNameAndLastName(String name, String lastName) {
        return estudentRepository.findByNameIgnoreCaseAndLastNameIgnoreCase(name, lastName);
    }

}
