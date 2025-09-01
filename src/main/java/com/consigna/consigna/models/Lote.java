package com.consigna.consigna.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Lote")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"pecas"})
@EqualsAndHashCode(exclude = {"pecas"})
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Column(name = "status")
    private String status;

    @Column(name = "valor_total")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fk_Consignatario_id", nullable = false)
    private Consignatario consignatario;

    @ManyToOne
    @JoinColumn(name = "fk_Usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Peca> pecas;

}
