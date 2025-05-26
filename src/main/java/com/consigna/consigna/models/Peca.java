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
    private Integer status;

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
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "fk_Categoria_id", nullable = false)
    private Categoria categoria;

    // Getters e Setters


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Peca peca = (Peca) o;
        return Objects.equals(id, peca.id) && Objects.equals(nome, peca.nome) && Objects.equals(valorSolicitado, peca.valorSolicitado) && Objects.equals(valorMinimo, peca.valorMinimo) && Objects.equals(status, peca.status) && Objects.equals(palavrasChave, peca.palavrasChave) && Objects.equals(valorDeVenda, peca.valorDeVenda) && Objects.equals(dataAlteracaoStatus, peca.dataAlteracaoStatus) && Objects.equals(dataPagamentoConsignatario, peca.dataPagamentoConsignatario) && Objects.equals(lote, peca.lote) && Objects.equals(categoria, peca.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valorSolicitado, valorMinimo, status, palavrasChave, valorDeVenda, dataAlteracaoStatus, dataPagamentoConsignatario, lote, categoria);
    }
}
