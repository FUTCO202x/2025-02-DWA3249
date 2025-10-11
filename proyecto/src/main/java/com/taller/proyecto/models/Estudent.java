package com.taller.proyecto.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Estudent {
    private int id;
    private String name;
    private String lastName;
    private String email;
}
