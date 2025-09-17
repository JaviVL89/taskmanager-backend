package com.tareas.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "tareas")
public class Tarea {
    @Id
    private String id;

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    private String descripcion;

    private String estado;

    @NotBlank(message = "La prioridad es obligatoria.")
    private String prioridad;

    private List<Subtarea> subtareas;

    @NotNull(message = "La fecha límite es obligatoria.")
    @FutureOrPresent(message = "La fecha límite no puede ser anterior a hoy.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLimite;

    private String horaLimite;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;
}
