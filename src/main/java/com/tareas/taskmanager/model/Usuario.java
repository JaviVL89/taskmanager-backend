package com.tareas.taskmanager.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;

    private String nombre;
    private String apellidos;
    private String username;
    private String email;
    private String password;

    public Usuario(String nombre, String apellidos, String username, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

