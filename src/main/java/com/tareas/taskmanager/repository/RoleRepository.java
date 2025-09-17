package com.tareas.taskmanager.repository;

import com.tareas.taskmanager.model.ERole;
import com.tareas.taskmanager.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
