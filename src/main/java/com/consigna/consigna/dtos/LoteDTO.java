package com.consigna.consigna.dtos;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class LoteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private String codigo;
    private String descricao;
    private List<PecaDTO> pecas;
    private String status;
    private String valor_total;
    private Date data_entrada;
    private Date data_fechamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PecaDTO> getPecas() {
        return pecas;
    }

    public void setPecas(List<PecaDTO> pecas) {
        this.pecas = pecas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValor_total() {
        return valor_total;
    }

    public void setValor_total(String valor_total) {
        this.valor_total = valor_total;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LoteDTO loteDTO)) return false;
        return getId() == loteDTO.getId() && Objects.equals(getCodigo(), loteDTO.getCodigo()) && Objects.equals(getDescricao(), loteDTO.getDescricao()) && Objects.equals(getPecas(), loteDTO.getPecas()) && Objects.equals(getStatus(), loteDTO.getStatus()) && Objects.equals(getValor_total(), loteDTO.getValor_total()) && Objects.equals(getData_entrada(), loteDTO.getData_entrada()) && Objects.equals(getData_fechamento(), loteDTO.getData_fechamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCodigo(), getDescricao(), getPecas(), getStatus(), getValor_total(), getData_entrada(), getData_fechamento());
    }
}
