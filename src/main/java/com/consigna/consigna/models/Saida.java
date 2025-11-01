package com.consigna.consigna.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Saida")
@Getter
@Setter
@NoArgsConstructor
public class Saida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_saida", nullable = false)
    private LocalDate dataSaida;

    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "fk_Peca_id", nullable = false)
    private Peca peca;

    @ManyToOne
    @JoinColumn(name = "fk_Usuario_id", nullable = false)
    private Usuario usuario;
}
