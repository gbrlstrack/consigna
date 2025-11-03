package com.consigna.consigna.specifications;

import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.models.Lote;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.time.LocalTime;

public class LoteSpecification {

    public static Specification<Lote> comStatus(StatusPeca status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Lote> comDataEntradaEntre(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio != null && dataFim != null) {
                return criteriaBuilder.between(root.get("dataEntrada"), dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX));
            } else if (dataInicio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataEntrada"), dataInicio.atStartOfDay());
            } else if (dataFim != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("dataEntrada"), dataFim.atTime(LocalTime.MAX));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Lote> comDataSaidaEntre(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio != null && dataFim != null) {
                return criteriaBuilder.between(root.get("dataSaida"), dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX));
            } else if (dataInicio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataSaida"), dataInicio.atStartOfDay());
            } else if (dataFim != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("dataSaida"), dataFim.atTime(LocalTime.MAX));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Lote> comConsignatario(String nomeConsignatario) {
        return (root, query, criteriaBuilder) -> {
            if (nomeConsignatario == null || nomeConsignatario.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("consignatario").get("nome")), "%" + nomeConsignatario.toLowerCase() + "%");
        };
    }

    /**
     * Encontra lotes cujo período de atividade (da dataEntrada à dataSaida)
     * esteja totalmente contido dentro do intervalo de datas fornecido.
     * A data de entrada do lote deve ser maior ou igual à data de início do filtro.
     * A data de saída do lote deve ser menor ou igual à data de fim do filtro.
     * Lotes com data de saída nula não serão retornados por este filtro.
     */
    public static Specification<Lote> comPeriodoContidoEm(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio == null || dataFim == null) {
                // Este filtro requer ambas as datas para funcionar.
                return criteriaBuilder.conjunction();
            }

            Predicate entradaDepoisDeInicio = criteriaBuilder.greaterThanOrEqualTo(root.get("dataEntrada"), dataInicio.atStartOfDay());
            Predicate saidaAntesDeFim = criteriaBuilder.lessThanOrEqualTo(root.get("dataSaida"), dataFim.atTime(LocalTime.MAX));

            return criteriaBuilder.and(entradaDepoisDeInicio, saidaAntesDeFim);
        };
    }
}
