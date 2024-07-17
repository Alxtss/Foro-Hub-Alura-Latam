package com.aluracursos.foro_hub.domain.topico;

import com.aluracursos.foro_hub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        validarDuplicateEntry(datosRegistroTopico);
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico detallarTopico(Long id) {
        validar(id);
        Topico topico = topicoRepository.getReferenceById(id);
        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico actualizarTopico(Long id, DatosRegistroTopico datosRegistroTopico) {
        validar(id);
        Topico topico = topicoRepository.getReferenceById(id);
        validarDuplicateEntry(datosRegistroTopico);
        topico.actualizarDatos(datosRegistroTopico);
        return new DatosRespuestaTopico(topico);
    }

    public void eliminarTopico(Long id) {
        validar(id);
        topicoRepository.deleteById(id);
    }

    public void validar(Long id) {
        if (topicoRepository.findById(id).isEmpty()) {
            throw new ValidacionDeIntegridad("Este id para el tópico no fue encontrado");
        }
    }

    private void validarDuplicateEntry(DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTitulo(datosRegistroTopico.titulo())) {
            throw new ValidacionDeIntegridad("Ya hay un tópico con ese título, modifique su título");
        }
        if (topicoRepository.existsByMensaje(datosRegistroTopico.mensaje())) {
            throw new ValidacionDeIntegridad("Ya hay un tópico con ese mensaje, modifique su mensaje");
        }
    }
}
