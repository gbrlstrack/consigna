package com.consigna.consigna.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long id;
    private String codigo;
    private String descricao;
    private List<PecaDTO> pecas;
    private String status;
    private Double valorTotal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataEntrada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataFechamento;
    private Long consignatarioId;
    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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

    public Long getConsignatarioId() {
        return consignatarioId;
    }

    public void setConsignatarioId(Long consignatarioId) {
        this.consignatarioId = consignatarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LoteDTO loteDTO)) return false;
        return Objects.equals(getId(), loteDTO.getId()) && Objects.equals(getCodigo(), loteDTO.getCodigo()) && Objects.equals(getDescricao(), loteDTO.getDescricao()) && Objects.equals(getPecas(), loteDTO.getPecas()) && Objects.equals(getStatus(), loteDTO.getStatus()) && Objects.equals(getValorTotal(), loteDTO.getValorTotal()) && Objects.equals(getDataEntrada(), loteDTO.getDataEntrada()) && Objects.equals(getDataFechamento(), loteDTO.getDataFechamento()) && Objects.equals(getConsignatarioId(), loteDTO.getConsignatarioId()) && Objects.equals(getUsuarioId(), loteDTO.getUsuarioId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCodigo(), getDescricao(), getPecas(), getStatus(), getValorTotal(), getDataEntrada(), getDataFechamento(), getConsignatarioId(), getUsuarioId());
    }
}
