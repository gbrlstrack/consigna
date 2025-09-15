package com.consigna.consigna.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PecaSaidaDTORequest implements Serializable {
    private Long id;
    private String status;
    private Integer quantidade;
}
