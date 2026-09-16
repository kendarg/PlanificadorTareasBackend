package com.Bakend.PlanificadorTareasBackend.dto;

import lombok.Data;

@Data
public class ComentarioRequestDTO {
    private String contenido;
    private String autor;
    private Long publicacionId;
}
