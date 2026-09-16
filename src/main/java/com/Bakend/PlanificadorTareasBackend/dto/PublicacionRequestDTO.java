package com.Bakend.PlanificadorTareasBackend.dto;

import lombok.Data;

@Data
public class PublicacionRequestDTO {

    private String titulo;
    private String contenido;
    private Long aurtor;
}
