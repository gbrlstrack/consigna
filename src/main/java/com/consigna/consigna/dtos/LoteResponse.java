package com.consigna.consigna.dtos;


import lombok.Data;

import java.util.List;

@Data
public class LoteResponse {
    private Long id;
    private String codigo;
    private String descricao;
    private List<PecaResponse> pecas;
}
