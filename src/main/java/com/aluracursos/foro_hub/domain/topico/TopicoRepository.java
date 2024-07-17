package com.aluracursos.foro_hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //JPQL query para testing
    @Query("""
            select t from Topico t
            order by
            t.fechaCreacion
            desc
            """)
    Page<Topico> ordenarPorMasReciente(Pageable paginacion);

    //JPA method
//    Page<Topico> findAllByOrderByFechaCreacionDesc(Pageable paginacion);

    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);
}
