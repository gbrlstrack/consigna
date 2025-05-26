package com.consigna.consigna.models;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "usuario", length = 20)
    private String usuario;

    @Column(name = "senha", length = 20)
    private String senha;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(nome, usuario1.nome) && Objects.equals(usuario, usuario1.usuario) && Objects.equals(senha, usuario1.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario, senha);
    }
}
