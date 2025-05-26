package com.consigna.consigna.models;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column(name = "status")
    private Integer status;

    @Column(name = "valor_total")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fk_Consignatario_id", nullable = false)
    private Consignatario consignatario;

    @ManyToOne
    @JoinColumn(name = "fk_Usuario_id", nullable = false)
    private Usuario usuario;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Consignatario getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(Consignatario consignatario) {
        this.consignatario = consignatario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return Objects.equals(id, lote.id) && Objects.equals(dataEntrada, lote.dataEntrada) && Objects.equals(dataFechamento, lote.dataFechamento) && Objects.equals(status, lote.status) && Objects.equals(valorTotal, lote.valorTotal) && Objects.equals(consignatario, lote.consignatario) && Objects.equals(usuario, lote.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataEntrada, dataFechamento, status, valorTotal, consignatario, usuario);
    }
}
