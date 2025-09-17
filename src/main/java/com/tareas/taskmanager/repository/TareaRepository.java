package com.tareas.taskmanager.repository;

import com.tareas.taskmanager.model.Tarea;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TareaRepository extends MongoRepository<Tarea, String> {

    // Todas las tareas ordenadas por prioridad lógica y fecha
    @Aggregation(pipeline = {
        "{ $addFields: { prioridadOrden: { $switch: { branches: [ " +
        "{ case: { $eq: [ \"$prioridad\", \"alta\" ] }, then: 1 }, " +
        "{ case: { $eq: [ \"$prioridad\", \"media\" ] }, then: 2 }, " +
        "{ case: { $eq: [ \"$prioridad\", \"baja\" ] }, then: 3 } ], " +
        "default: 4 } } } }",
        "{ $sort: { prioridadOrden: 1, createdAt: 1 } }"
    })
    List<Tarea> findAllOrdenadasPorPrioridadYFecha();

    // Tareas filtradas por estado y ordenadas por prioridad lógica y fecha
    @Aggregation(pipeline = {
        "{ $match: { estado: ?0 } }",
        "{ $addFields: { prioridadOrden: { $switch: { branches: [ " +
        "{ case: { $eq: [ \"$prioridad\", \"alta\" ] }, then: 1 }, " +
        "{ case: { $eq: [ \"$prioridad\", \"media\" ] }, then: 2 }, " +
        "{ case: { $eq: [ \"$prioridad\", \"baja\" ] }, then: 3 } ], " +
        "default: 4 } } } }",
        "{ $sort: { prioridadOrden: 1, createdAt: 1 } }"
    })
    List<Tarea> findByEstadoOrdenadas(String estado);
}
