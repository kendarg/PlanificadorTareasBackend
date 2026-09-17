package com.Bakend.PlanificadorTareasBackend.repository;

import com.Bakend.PlanificadorTareasBackend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}