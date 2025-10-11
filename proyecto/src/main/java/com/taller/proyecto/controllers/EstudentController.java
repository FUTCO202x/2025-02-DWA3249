package com.taller.proyecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.proyecto.models.Estudent;
import com.taller.proyecto.models.DTO.EstudentResponseCreateDTO;
import com.taller.proyecto.services.interfaces.InterfaceEstudent;

@RestController()
@RequestMapping("/api")
public class EstudentController {

    private final InterfaceEstudent estudentService;

    public EstudentController(InterfaceEstudent estudentService) {
        this.estudentService = estudentService;
    }

    @PostMapping("/create")
    public ResponseEntity<EstudentResponseCreateDTO> createEstudent(@RequestBody Estudent estudent){
        EstudentResponseCreateDTO created = estudentService.createEstudent(estudent);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Estudent>> getAllEstudents(){
        List<Estudent> students = estudentService.getAllEstudents();
        if (students.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudent> getEstudentById(@PathVariable int id){
        Optional<Estudent> student = estudentService.getEstudentById(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/params")
    public ResponseEntity<Map<String,String>>getEstudentByNameAndLastName(
        @RequestParam String name,
        @RequestParam String lastName
    ){
        Optional<Estudent> student = estudentService.getEstudentByNameAndLastName(name, lastName);
        if (student.isPresent()){
            Map<String,String> response = new HashMap<>();
            response.put("NombreCompleto", student.get().getName() + " " + student.get().getLastName());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
