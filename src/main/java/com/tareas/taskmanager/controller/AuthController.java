package com.tareas.taskmanager.controller;

import com.tareas.taskmanager.model.Usuario;
import com.tareas.taskmanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody Usuario user) {
    try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioRepository.save(user);
        return ResponseEntity.ok("Usuario registrado");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("Error al registrar el usuario");
    }
}

}
