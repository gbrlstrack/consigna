package com.consigna.consigna.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataAlteracaoStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private Date dataPagamentoConsignatario;

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Double getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(Double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public Date getDataAlteracaoStatus() {
        return dataAlteracaoStatus;
    }

    public void setDataAlteracaoStatus(Date dataAlteracaoStatus) {
        this.dataAlteracaoStatus = dataAlteracaoStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PecaDTO pecaDTO)) return false;
        return Objects.equals(getId(), pecaDTO.getId()) && Objects.equals(getNome(), pecaDTO.getNome()) && Objects.equals(getQuantidade(), pecaDTO.getQuantidade()) && Objects.equals(getValorSolicitado(), pecaDTO.getValorSolicitado()) && Objects.equals(getValorMinimo(), pecaDTO.getValorMinimo()) && Objects.equals(getStatus(), pecaDTO.getStatus()) && Objects.equals(getPalavrasChave(), pecaDTO.getPalavrasChave()) && Objects.equals(getValorDeVenda(), pecaDTO.getValorDeVenda()) && Objects.equals(getDataAlteracaoStatus(), pecaDTO.getDataAlteracaoStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getQuantidade(), getValorSolicitado(), getValorMinimo(), getStatus(), getPalavrasChave(), getValorDeVenda(), getDataAlteracaoStatus());
    }
}
