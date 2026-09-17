package com.Bakend.PlanificadorTareasBackend.service;

import com.Bakend.PlanificadorTareasBackend.dto.ComentarioRequestDTO;
import com.Bakend.PlanificadorTareasBackend.dto.ComentarioResponseDTO;
import com.Bakend.PlanificadorTareasBackend.exception.ResourceNotFoundException;
import com.Bakend.PlanificadorTareasBackend.model.Comentario;
import com.Bakend.PlanificadorTareasBackend.model.Publicacion;
import com.Bakend.PlanificadorTareasBackend.repository.ComentarioRepository;
import com.Bakend.PlanificadorTareasBackend.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, PublicacionRepository publicacionRepository) {
        this.comentarioRepository = comentarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    public ComentarioResponseDTO crearComentario(ComentarioRequestDTO requestDTO) {
        Publicacion publicacion = publicacionRepository.findById(requestDTO.getPublicacionId())
                .orElseThrow(() -> new ResourceNotFoundException("No se puede comentar. Publicación no encontrada con ID: " + requestDTO.getPublicacionId()));

        Comentario comentario = new Comentario();
        comentario.setContenido(requestDTO.getContenido());
        comentario.setAutor(requestDTO.getAutor());
        comentario.setPublicacion(publicacion);

        Comentario guardado = comentarioRepository.save(comentario);
        return mapearAResponseDTO(guardado);
    }

    public void eliminarComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentario no encontrado con ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }

    private ComentarioResponseDTO mapearAResponseDTO(Comentario comentario) {
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setId(comentario.getId());
        dto.setContenido(comentario.getContenido());
        dto.setAutor(comentario.getAutor());
        dto.setFechaCreacion(comentario.getFechaCreacion());
        return dto;
    }
}