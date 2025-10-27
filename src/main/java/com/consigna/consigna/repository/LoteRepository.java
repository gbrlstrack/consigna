package com.consigna.consigna.repository;

import com.consigna.consigna.models.Lote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    @Query("SELECT l FROM Lote l LEFT JOIN FETCH l.pecas WHERE l.id = :id")
    Optional<Lote> findByIdWithPecas(@Param("id") Long id);

    @Query(value = "SELECT DISTINCT l FROM Lote l LEFT JOIN FETCH l.pecas",
            countQuery = "SELECT COUNT(DISTINCT l.id) FROM Lote l")
    Page<Lote> findAllWithPecas(Pageable pageable);
}

