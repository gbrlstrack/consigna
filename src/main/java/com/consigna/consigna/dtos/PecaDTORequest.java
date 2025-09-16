package com.consigna.consigna.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PecaDTORequest implements Serializable {
    private Long id;
    private String descricao;
    private Integer quantidade;
    private Double valorMinimo;
    private String status;
    private String palavrasChave;
    private Double valorDeVenda;
    private Double valorDeRepasse;
}
