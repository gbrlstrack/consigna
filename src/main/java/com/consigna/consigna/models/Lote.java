package com.consigna.consigna.models;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_entrada")
    private Date dataEntrada;

    @Column(name = "data_fechamento")
    private Date dataFechamento;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(List<Peca> pecas) {
        this.pecas = pecas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lote lote)) return false;
        return Objects.equals(getId(), lote.getId()) && Objects.equals(getDataEntrada(), lote.getDataEntrada()) && Objects.equals(getDataFechamento(), lote.getDataFechamento()) && Objects.equals(getStatus(), lote.getStatus()) && Objects.equals(getValorTotal(), lote.getValorTotal()) && Objects.equals(getConsignatario(), lote.getConsignatario()) && Objects.equals(getUsuario(), lote.getUsuario()) && Objects.equals(getPecas(), lote.getPecas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataEntrada(), getDataFechamento(), getStatus(), getValorTotal(), getConsignatario(), getUsuario(), getPecas());
    }
}
