package com.consigna.consigna.repository;

import com.consigna.consigna.models.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    @Query("SELECT l FROM Lote l LEFT JOIN FETCH l.pecas WHERE l.id = :id")
    Optional<Lote> findByIdWithPecas(@Param("id") Long id);

    @Query("SELECT DISTINCT l FROM Lote l LEFT JOIN FETCH l.pecas")
    List<Lote> findAllWithPecas();
}

