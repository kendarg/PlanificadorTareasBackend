package com.Bakend.PlanificadorTareasBackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComentarioResponseDTO {
    private Long id;
    private String contenido;
    private String autor;
    private LocalDateTime fechaCreacion;
}
