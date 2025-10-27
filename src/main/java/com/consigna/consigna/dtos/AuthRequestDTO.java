package com.consigna.consigna.dtos;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String login;
    private String senha;
}
