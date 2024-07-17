package com.aluracursos.foro_hub.domain.topico;

import lombok.Getter;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        @Getter
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
    }
}
