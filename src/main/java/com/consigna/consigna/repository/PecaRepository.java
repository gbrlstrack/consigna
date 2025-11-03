package com.consigna.consigna.repository;

import com.consigna.consigna.models.Peca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {

    Page<Peca> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    Page<Peca> findByDescricaoContainingIgnoreCaseAndStatusNot(String descricao, String status, Pageable pageable);

    Page<Peca> findAllByStatusNot(String status, Pageable pageable);
}
