package com.Bakend.PlanificadorTareasBackend.service;

import com.Bakend.PlanificadorTareasBackend.dto.TareasRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.TareasResponseDTO;
import com.Bakend.PlanificadorTareasBackend.exception.ResourceNotFoundException;
import com.Bakend.PlanificadorTareasBackend.model.Tarea;
import com.Bakend.PlanificadorTareasBackend.repository.TareasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareasService {

    private final TareasRepository tareasRepository;

    public TareaService(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    public List<TareasResponseDTO> obtenerTodas() {
        return tareasRepository.findAll().stream()
                .map(this.mapearAResponseDTO)
                .collect(Collectors.toList());
    }

    public TareasResponseDTO obtenerPorId(Long id) {
        Tarea tarea = tareasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
        return mapearAResponseDTO(tarea);
    }

    public TareasResponseDTO crear(TareasRequestDTO requestDTO) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(requestDTO.getTitulo());
        tarea.setDescripcion(requestDTO.getDescripcion());
        tarea.setPrioridad(requestDTO.getPrioridad());
        tarea.setFechaLimite(requestDTO.getFechaLimite());
        tarea.setCompletada(false); // Por defecto nace incompleta

        Tarea tareaGuardada = tareasRepository.save(tarea);
        return mapearAResponseDTO(tareaGuardada);
    }

    public TareasResponseDTO actualizar(Long id, TareasRequestDTO requestDTO) {
        Tarea tarea = tareasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));

        tarea.setTitulo(requestDTO.getTitulo());
        tarea.setDescripcion(requestDTO.getDescripcion());
        tarea.setPrioridad(requestDTO.getPrioridad());
        tarea.setFechaLimite(requestDTO.getFechaLimite());

        Tarea tareaActualizada = tareasRepository.save(tarea);
        return mapearAResponseDTO(tareaActualizada);
    }

    public void eliminar(Long id) {
        if (!tareasRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Tarea no encontrada con ID: " + id);
        }
        tareasRepository.deleteById(id);
    }

    // Método auxiliar para mapear de Entidad a DTO de respuesta
    private TareasResponseDTO mapearAResponseDTO(Tarea tarea) {
        TareasResponseDTO dto = new TareasResponseDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setPrioridad(tarea.getPrioridad());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setCompletada(tarea.isCompletada());
        return dto;
    }
}
