package com.consigna.consigna.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PecaDTOResponse implements Serializable {
    private Long id;
    private String descricao;
    private Integer quantidade;
    private Double valorMinimo;
    private String status;
    private String palavrasChave;
    private Double valorDeVenda;
    private Double valorDeRepasse;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dataAlteracaoStatus;
    private Long loteId;
}
