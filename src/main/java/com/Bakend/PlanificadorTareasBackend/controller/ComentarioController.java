package com.Bakend.PlanificadorTareasBackend.controller;


import com.Bakend.PlanificadorTareasBackend.dto.ComentarioRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.ComentarioResponseDTO;
import com.Bakend.PlanificadorTareasBackend.service.PublicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    private final PublicacionService publicacionService;

    public ComentarioController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> crearComentario(@RequestBody ComentarioRequestDTO requestDTO) {
        ComentarioResponseDTO nuevoComentario = publicacionService.agregarComentario(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }
}