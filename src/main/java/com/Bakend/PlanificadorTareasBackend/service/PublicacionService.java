package com.Bakend.PlanificadorTareasBackend.service;


import com.Bakend.PlanificadorTareasBackend.dto.*;
import com.Bakend.PlanificadorTareasBackend.exception.ResourceNotFoundException;
import com.Bakend.PlanificadorTareasBackend.model.Comentario;
import com.Bakend.PlanificadorTareasBackend.model.Publicacion;
import com.Bakend.PlanificadorTareasBackend.repository.ComentarioRepository;
import com.Bakend.PlanificadorTareasBackend.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final ComentarioRepository comentarioRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, ComentarioRepository comentarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public List<PublicacionResponseDTO> obtenerTodas() {
        return publicacionRepository.findAll().stream()
                .map(this::mapearAResponseDTO)
                .collect(Collectors.toList());
    }

    public PublicacionResponseDTO obtenerPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicación no encontrada con ID: " + id));
        return mapearAResponseDTO(publicacion);
    }

    public PublicacionResponseDTO crear(PublicacionRequestDTO requestDTO) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(requestDTO.getTitulo());
        publicacion.setContenido(requestDTO.getContenido());
        publicacion.setAutor(String.valueOf(requestDTO.getAutor()));

        Publicacion guardada = publicacionRepository.save(publicacion);
        return mapearAResponseDTO(guardada);
    }

    public ComentarioResponseDTO agregarComentario(ComentarioRequestDTO requestDTO) {
        Publicacion publicacion = publicacionRepository.findById(requestDTO.getPublicacionId())
                .orElseThrow(() -> new ResourceNotFoundException("No se puede comentar. Publicación no encontrada."));

        Comentario comentario = new Comentario();
        comentario.setContenido(requestDTO.getContenido());
        comentario.setAutor(requestDTO.getAutor());
        comentario.setPublicacion(publicacion);

        Comentario guardado = comentarioRepository.save(comentario);

        ComentarioResponseDTO responseDTO = new ComentarioResponseDTO();
        responseDTO.setId(guardado.getId());
        responseDTO.setContenido(guardado.getContenido());
        responseDTO.setAutor(guardado.getAutor());
        responseDTO.setFechaCreacion(guardado.getFechaCreacion());
        return responseDTO;
    }

    public void eliminar(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publicación no encontrada para eliminar con ID: " + id);
        }
        publicacionRepository.deleteById(id);
    }

    private PublicacionResponseDTO mapearAResponseDTO(Publicacion pub) {
        PublicacionResponseDTO dto = new PublicacionResponseDTO();
        dto.setId(pub.getId());
        dto.setTitulo(pub.getTitulo());
        dto.setContenido(pub.getContenido());
        dto.setAutor(pub.getAutor());
        dto.setFechaCreacion(pub.getFechaCreacion());

        if (pub.getComentarios() != null) {
            List<ComentarioResponseDTO> comentariosDTO = pub.getComentarios().stream().map(c -> {
                ComentarioResponseDTO cDto = new ComentarioResponseDTO();
                cDto.setId(c.getId());
                cDto.setContenido(c.getContenido());
                cDto.setAutor(c.getAutor());
                cDto.setFechaCreacion(c.getFechaCreacion());
                return cDto;
            }).collect(Collectors.toList());
            dto.setComentarios(comentariosDTO);
        }
        return dto;
    }

}