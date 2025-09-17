package com.tareas.taskmanager.controller;

import com.tareas.taskmanager.model.Tarea;
import com.tareas.taskmanager.model.Subtarea;
import com.tareas.taskmanager.repository.TareaRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    // Obtener todas las tareas ordenadas por prioridad lógica y luego fecha
    @GetMapping
    public List<Tarea> getAll() {
        return tareaRepository.findAllOrdenadasPorPrioridadYFecha();
    }

    // Obtener tareas por estado (ordenadas por prioridad lógica y fecha)
@GetMapping("/estado/{estado}")
public List<Tarea> getByEstado(@PathVariable String estado) {
    return tareaRepository.findByEstadoOrdenadas(estado);
}


    // Crear nueva tarea
    @PostMapping
    public Tarea create(@Valid @RequestBody Tarea tarea) {
        tarea.setEstado("pendiente"); // Estado por defecto
        tarea.setCreatedAt(LocalDateTime.now()); // Fecha de creación
        return tareaRepository.save(tarea);
    }

    // Actualizar tarea
    @PutMapping("/{id}")
    public Tarea update(@PathVariable String id, @Valid @RequestBody Tarea tarea) {
        tarea.setId(id);
        return tareaRepository.save(tarea);
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        tareaRepository.deleteById(id);
    }

    // Agregar subtarea
    @PostMapping("/{tareaId}/subtareas")
    public Tarea agregarSubtarea(@PathVariable String tareaId, @RequestBody Subtarea subtarea) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(tareaId);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            if (tarea.getSubtareas() == null) {
                tarea.setSubtareas(new ArrayList<>());
            }
            subtarea.setId(UUID.randomUUID().toString());
            tarea.getSubtareas().add(subtarea);
            return tareaRepository.save(tarea);
        }
        throw new RuntimeException("Tarea no encontrada");
    }

    // Editar subtarea
    @PutMapping("/{tareaId}/subtareas/{subtareaId}")
    public Tarea editarSubtarea(@PathVariable String tareaId, @PathVariable String subtareaId, @RequestBody Subtarea subtareaActualizada) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(tareaId);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            List<Subtarea> subtareas = tarea.getSubtareas();
            for (int i = 0; i < subtareas.size(); i++) {
                if (subtareas.get(i).getId().equals(subtareaId)) {
                    subtareaActualizada.setId(subtareaId);
                    subtareas.set(i, subtareaActualizada);
                    return tareaRepository.save(tarea);
                }
            }
            throw new RuntimeException("Subtarea no encontrada");
        }
        throw new RuntimeException("Tarea no encontrada");
    }

    // Eliminar subtarea
    @DeleteMapping("/{tareaId}/subtareas/{subtareaId}")
    public Tarea eliminarSubtarea(@PathVariable String tareaId, @PathVariable String subtareaId) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(tareaId);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.getSubtareas().removeIf(s -> s.getId().equals(subtareaId));
            return tareaRepository.save(tarea);
        }
        throw new RuntimeException("Tarea no encontrada");
    }
}
