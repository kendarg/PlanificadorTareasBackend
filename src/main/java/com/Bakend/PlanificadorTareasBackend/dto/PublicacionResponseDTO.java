package com.Bakend.PlanificadorTareasBackend.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacionResponseDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private String autor;
    private LocalDateTime fechaCreacion;
    private List<ComentarioResponseDTO> comentarios;
}