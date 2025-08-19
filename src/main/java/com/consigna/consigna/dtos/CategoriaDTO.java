package com.consigna.consigna.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaDTO implements Serializable {

    private Long id;
    private String nome;
}
