package com.Bakend.PlanificadorTareasBackend.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TareasResponseDTO {
    private String titulo;
    private String descripcion;
    private String prioridad;
    private LocalDate fechaLimite;
}
