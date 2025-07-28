package com.consigna.consigna.repository;

import com.consigna.consigna.dtos.PecaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PecaRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public PecaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PecaResponse> listarPecasPorLote(Long loteId) {
        String sql = "SELECT id, nome, quantidade FROM peca WHERE lote_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PecaResponse.class), loteId);
    }
}
