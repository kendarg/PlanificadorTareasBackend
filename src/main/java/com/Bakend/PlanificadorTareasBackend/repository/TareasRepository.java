package com.Bakend.PlanificadorTareasBackend.repository;

import com.Bakend.PlanificadorTareasBackend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends JpaRepository<Tarea, Long> {
}
