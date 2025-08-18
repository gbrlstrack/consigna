package com.consigna.consigna.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Consignatario")
public class Consignatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "telefone", length = 14)
    private String telefone;

    @Column(name = "documento")
    private String documento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consignatario that = (Consignatario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(tipo, that.tipo) && Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipo, telefone);
    }
}
