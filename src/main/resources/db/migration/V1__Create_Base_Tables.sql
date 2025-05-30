CREATE TABLE IF NOT EXISTS Consignatario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    tipo VARCHAR(20),
    telefone VARCHAR(14)
);

CREATE TABLE IF NOT EXISTS Categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    usuario VARCHAR(20),
    senha VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Lote (
    id SERIAL PRIMARY KEY,
    data_entrada DATE,
    data_fechamento DATE,
    status INTEGER,
    valor_total NUMERIC(10,2),
    fk_Consignatario_id INTEGER,
    fk_Usuario_id INTEGER,
    CONSTRAINT FK_Lote_2 FOREIGN KEY (fk_Consignatario_id) REFERENCES Consignatario (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Lote_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Peca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    valor_solicitado NUMERIC(10,2),
    valor_minimo NUMERIC(10,2),
    status INTEGER,
    palavras_chave VARCHAR(50),
    valor_de_venda NUMERIC(10,2),
    data_alteracao_status DATE,
    data_pagamento_consignatario DATE,
    fk_Lote_id INTEGER,
    fk_Categoria_id INTEGER,
    CONSTRAINT FK_Peca_2 FOREIGN KEY (fk_Lote_id) REFERENCES Lote (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Peca_3 FOREIGN KEY (fk_Categoria_id) REFERENCES Categoria (id) ON DELETE RESTRICT
);
