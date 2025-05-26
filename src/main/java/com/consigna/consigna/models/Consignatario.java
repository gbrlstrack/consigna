package com.consigna.consigna.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "consignatario")
public class Consignatario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
