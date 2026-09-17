package com.Bakend.PlanificadorTareasBackend.repository;


import com.Bakend.PlanificadorTareasBackend.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}