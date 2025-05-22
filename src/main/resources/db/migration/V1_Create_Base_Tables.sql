CREATE TABLE Consignatario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    tipo VARCHAR(20),
    telefone VARCHAR(14)
);

CREATE TABLE Categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20)
);

CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    usuario VARCHAR(20),
    senha VARCHAR(20)
);

CREATE TABLE Lote (
    id SERIAL PRIMARY KEY,
    data_entrada DATE,
    data_fechamento DATE,
    status INTEGER,
    valor_total DOUBLE PRECISION,
    fk_Consignatario_id INTEGER,
    fk_Usuario_id INTEGER,
    CONSTRAINT FK_Lote_2 FOREIGN KEY (fk_Consignatario_id) REFERENCES Consignatario (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Lote_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE RESTRICT
);

CREATE TABLE Peca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    valor_solicitado DOUBLE PRECISION,
    valor_minimo DOUBLE PRECISION,
    status INTEGER,
    palavras_chave VARCHAR(50),
    valor_de_venda DOUBLE PRECISION,
    data_alteracao_status DATE,
    data_pagamento_consignatario DATE,
    fk_Lote_id INTEGER,
    fk_Categoria_id INTEGER,
    CONSTRAINT FK_Peca_2 FOREIGN KEY (fk_Lote_id) REFERENCES Lote (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Peca_3 FOREIGN KEY (fk_Categoria_id) REFERENCES Categoria (id) ON DELETE RESTRICT
);
