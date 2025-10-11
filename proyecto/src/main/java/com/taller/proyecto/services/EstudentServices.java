package com.taller.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.taller.proyecto.models.Estudent;
import com.taller.proyecto.models.DTO.EstudentResponseCreateDTO;
import com.taller.proyecto.services.interfaces.InterfaceEstudent;

@Service
public class EstudentServices implements InterfaceEstudent {
    private final List<Estudent> students = new java.util.ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);


    @Override
    public EstudentResponseCreateDTO   createEstudent(Estudent estudent) {
        if (estudent == null) return null;

        String name = estudent.getName() == null ? "" : estudent.getName().trim().toUpperCase();
        String lastName = estudent.getLastName() == null ? "" : estudent.getLastName().trim().toUpperCase();
        
        
        estudent.setName(name);
        estudent.setLastName(lastName);
        estudent.setId(idCounter.getAndIncrement());

        synchronized (students) {
            students.add(estudent);
        }

        return new EstudentResponseCreateDTO(name, lastName);
    }

    @Override
    public List<Estudent> getAllEstudents() {
        synchronized (students) {
            return new ArrayList<>(students);
        }
    }

    @Override
    public Optional<Estudent> getEstudentById(int id) {
        synchronized (students) {
            return students.stream()
                    .filter(student -> student.getId() == id)
                    .findFirst();
        }
    }

    @Override
    public Optional<Estudent> getEstudentByNameAndLastName(String name, String lastName) {
        synchronized (students) {
            return students.stream()
                    .filter(std -> std.getName() != null && std.getLastName() != null)
                    .filter(std -> std.getName().equalsIgnoreCase(name) && std.getLastName().equalsIgnoreCase(lastName))
                    .findFirst();
        }
    }

}
