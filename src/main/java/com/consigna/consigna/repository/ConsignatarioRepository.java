package com.consigna.consigna.repository;

import com.consigna.consigna.models.Consignatario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignatarioRepository extends JpaRepository<Consignatario, Long> {
    Page<Consignatario> findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCase(String nome, String documento, Pageable pageable);
}
