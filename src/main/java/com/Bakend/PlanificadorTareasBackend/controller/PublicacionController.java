package com.Bakend.PlanificadorTareasBackend.controller;

import com.Bakend.PlanificadorTareasBackend.dto.ComentarioRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.ComentarioResponseDTO;
import com.Bakend.PlanificadorTareasBackend.dto.PublicacionRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.PublicacionResponseDTO;
import com.Bakend.PlanificadorTareasBackend.service.PublicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(publicacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionResponseDTO> crearPublicacion(@RequestBody PublicacionRequestDTO requestDTO) {
        PublicacionResponseDTO nuevaPub = publicacionService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPub);
    }

    @PostMapping("/comentarios")
    public ResponseEntity<ComentarioResponseDTO> agregarComentario(@RequestBody ComentarioRequestDTO requestDTO) {
        ComentarioResponseDTO nuevoComentario = publicacionService.agregarComentario(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}