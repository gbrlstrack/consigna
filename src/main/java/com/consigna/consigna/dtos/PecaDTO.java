package com.consigna.consigna.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class PecaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valorSolicitado;
    private Double valorMinimo;
    private String status;
    private List<String> palavrasChave;
    private Double valorDeVenda;
    private Double valorDRepasse;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dataAlteracaoStatus;
}
