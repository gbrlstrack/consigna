package com.consigna.consigna.dtos;

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

    private long id;
    private String nome;
    private Integer quantidade;
    private Double valor_solicitado;
    private Double valor_minimo;
    private String status;
    private List<String> palavras_chave;
    private Double valor_de_venda;
    private Date data_alteracao_status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Double getValor_solicitado() {
        return valor_solicitado;
    }

    public void setValor_solicitado(Double valor_solicitado) {
        this.valor_solicitado = valor_solicitado;
    }

    public Double getValor_minimo() {
        return valor_minimo;
    }

    public void setValor_minimo(Double valor_minimo) {
        this.valor_minimo = valor_minimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPalavras_chave() {
        return palavras_chave;
    }

    public void setPalavras_chave(List<String> palavras_chave) {
        this.palavras_chave = palavras_chave;
    }

    public Double getValor_de_venda() {
        return valor_de_venda;
    }

    public void setValor_de_venda(Double valor_de_venda) {
        this.valor_de_venda = valor_de_venda;
    }

    public Date getData_alteracao_status() {
        return data_alteracao_status;
    }

    public void setData_alteracao_status(Date data_alteracao_status) {
        this.data_alteracao_status = data_alteracao_status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PecaDTO pecaDTO)) return false;
        return getId() == pecaDTO.getId() && Objects.equals(getNome(), pecaDTO.getNome()) && Objects.equals(getQuantidade(), pecaDTO.getQuantidade()) && Objects.equals(getValor_solicitado(), pecaDTO.getValor_solicitado()) && Objects.equals(getValor_minimo(), pecaDTO.getValor_minimo()) && Objects.equals(getStatus(), pecaDTO.getStatus()) && Objects.equals(getPalavras_chave(), pecaDTO.getPalavras_chave()) && Objects.equals(getValor_de_venda(), pecaDTO.getValor_de_venda()) && Objects.equals(getData_alteracao_status(), pecaDTO.getData_alteracao_status());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getQuantidade(), getValor_solicitado(), getValor_minimo(), getStatus(), getPalavras_chave(), getValor_de_venda(), getData_alteracao_status());
    }
}
