package com.Bakend.PlanificadorTareasBackend.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TareasResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private LocalDate fechaLimite;
    private boolean completada;
}
