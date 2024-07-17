package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópico", description = "Contiene las funcionalidades de CRUD de tópicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registra un tópico en la base de datos",
            description = "")
//            tags = {"consulta", "post"})
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaTopico datosRespuestaTopico = service.registrarTopico(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuestaTopico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    @Operation(
            summary = "Lista los tópicos registrados",
            description = "")
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.ordenarPorMasReciente(paginacion).map(DatosRespuestaTopico::new));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Detalla un tópico registrado",
            description = "")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        DatosRespuestaTopico datosRespuestaTopico = service.detallarTopico(id);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Actualiza un tópico registrado",
            description = "")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        DatosRespuestaTopico datosRespuestaTopico = service.actualizarTopico(id, datosRegistroTopico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina un tópico de la base de datos",
            description = "")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
//        topicoRepository.deleteById(id);
        service.eliminarTopico(id);
        return ResponseEntity.ok("Tópico eliminado");
    }
}
