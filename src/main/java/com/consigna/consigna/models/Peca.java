package com.consigna.consigna.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Peca")
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

    @Column(name = "data_alteracao_status")
    private LocalDate dataAlteracaoStatus;

    @Column(name = "data_pagamento_consignatario")
    private LocalDate dataPagamentoConsignatario;

    @ManyToOne
    @JoinColumn(name = "fk_Lote_id", nullable = false)
    private Lote loteId;

    @ManyToOne
    @JoinColumn(name = "fk_Categoria_id", nullable = false)
    private Categoria categoria;

    @Column
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(Double valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Double getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(Double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public LocalDate getDataAlteracaoStatus() {
        return dataAlteracaoStatus;
    }

    public void setDataAlteracaoStatus(LocalDate dataAlteracaoStatus) {
        this.dataAlteracaoStatus = dataAlteracaoStatus;
    }

    public LocalDate getDataPagamentoConsignatario() {
        return dataPagamentoConsignatario;
    }

    public void setDataPagamentoConsignatario(LocalDate dataPagamentoConsignatario) {
        this.dataPagamentoConsignatario = dataPagamentoConsignatario;
    }

    public Lote getLoteId() {
        return loteId;
    }

    public void setLoteId(Lote loteId) {
        this.loteId = loteId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Peca peca)) return false;
        return getQuantidade() == peca.getQuantidade() && Objects.equals(getId(), peca.getId()) && Objects.equals(getNome(), peca.getNome()) && Objects.equals(getValorSolicitado(), peca.getValorSolicitado()) && Objects.equals(getValorMinimo(), peca.getValorMinimo()) && Objects.equals(getStatus(), peca.getStatus()) && Objects.equals(getPalavrasChave(), peca.getPalavrasChave()) && Objects.equals(getValorDeVenda(), peca.getValorDeVenda()) && Objects.equals(getDataAlteracaoStatus(), peca.getDataAlteracaoStatus()) && Objects.equals(getDataPagamentoConsignatario(), peca.getDataPagamentoConsignatario()) && Objects.equals(getLoteId(), peca.getLoteId()) && Objects.equals(getCategoria(), peca.getCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getValorSolicitado(), getValorMinimo(), getStatus(), getPalavrasChave(), getValorDeVenda(), getDataAlteracaoStatus(), getDataPagamentoConsignatario(), getLoteId(), getCategoria(), getQuantidade());
    }
}
