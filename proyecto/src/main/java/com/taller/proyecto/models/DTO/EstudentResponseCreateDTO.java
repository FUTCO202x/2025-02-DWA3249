package com.taller.proyecto.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstudentResponseCreateDTO {
    private String name;
    private String lastName;
}
