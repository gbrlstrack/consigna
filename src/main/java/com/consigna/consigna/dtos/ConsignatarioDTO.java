package com.consigna.consigna.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsignatarioDTO implements Serializable {

    private Long id;
    private String nome;
    private String tipo;
    private String telefone;
    private String tipoDocumento;
    private String documento;
}
