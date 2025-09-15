package com.consigna.consigna.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {
    private Long id;
    private String nome;
    private String login;
    private String senha;
}
