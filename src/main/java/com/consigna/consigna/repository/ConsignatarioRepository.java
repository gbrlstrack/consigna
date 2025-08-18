package com.consigna.consigna.repository;

import com.consigna.consigna.models.Consignatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsignatarioRepository extends JpaRepository<Consignatario, Long> {
}
