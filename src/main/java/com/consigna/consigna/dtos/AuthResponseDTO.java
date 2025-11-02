package com.consigna.consigna.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO implements Serializable {
    private String token;
    private Long userId;
}