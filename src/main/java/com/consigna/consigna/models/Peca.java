package com.consigna.consigna.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Peca")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"lote"})
@EqualsAndHashCode(exclude = {"lote"})
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "valor_solicitado")
    private Double valorSolicitado;

    @Column(name = "valor_minimo")
    private Double valorMinimo;

    @Column(name = "status")
    private String status;

    @Column(name = "palavras_chave", length = 50)
    private String palavrasChave;

    @Column(name = "valor_de_venda")
    private Double valorDeVenda;

    @Column(name = "valor_de_repasse")
    private Double valorDeRepasse;

    @Column(name = "data_alteracao_status")
    private LocalDateTime dataAlteracaoStatus;

    @ManyToOne
    @JoinColumn(name = "fk_Lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "fk_Categoria_id")
    private Categoria categoria;

    @Column
    private Integer quantidade;

}
