package com.consigna.consigna.dtos;

import lombok.Data;
import java.util.List;

@Data
public class LoteRequest {
    private String codigo;
    private String descricao;
    private List<PecaRequest> pecas; // opcional, se quiser enviar informações adicionais das peças
}
