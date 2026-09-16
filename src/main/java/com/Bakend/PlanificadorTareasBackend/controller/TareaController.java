package com.Bakend.PlanificadorTareasBackend.controller;

import com.Bakend.PlanificadorTareasBackend.dto.TareasRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.TareasResponseDTO;
import com.Bakend.PlanificadorTareasBackend.service.TareasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")


public class TareaController {
    private final TareasService tareasService;

    public TareaController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    @GetMapping
    public ResponseEntity<List<TareasResponseDTO>> listarTodas() {
        return ResponseEntity.ok(tareasService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<TareasResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseEntity.ok(tareasService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<TareasResponseDTO> crearTarea(@RequestBody TareasRequestDTO requestDTO) {
        TareasResponseDTO nuevaTarea = tareasService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareasResponseDTO> actualizarTarea(@PathVariable Long id, @RequestBody TareasRequestDTO requestDTO) {
        TareasResponseDTO tareaActualizada = tareasService.actualizar(id, requestDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
