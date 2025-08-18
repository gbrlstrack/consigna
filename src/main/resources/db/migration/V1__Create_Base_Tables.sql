CREATE TABLE IF NOT EXISTS Consignatario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    tipo_documento VARCHAR(11),
    documento VARCHAR(14)
);

CREATE TABLE IF NOT EXISTS Categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    usuario VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Lote (
    id SERIAL PRIMARY KEY,
    data_entrada DATE DEFAULT CURRENT_DATE,
    data_fechamento DATE,
    status VARCHAR(15),
    valor_total NUMERIC(10,2),
    fk_Consignatario_id INTEGER NOT NULL,
    fk_Usuario_id INTEGER NOT NULL,
    CONSTRAINT FK_Lote_2 FOREIGN KEY (fk_Consignatario_id) REFERENCES Consignatario (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Lote_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Peca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    valor_solicitado NUMERIC(10,2) NOT NULL,
    valor_minimo NUMERIC(10,2) NOT NULL,
    status INTEGER,
    palavras_chave VARCHAR(50),
    valor_de_venda NUMERIC(10,2),
    data_alteracao_status DATE,
    quantidade INTEGER NOT NULL DEFAULT 1,
    fk_Lote_id INTEGER,
    fk_Categoria_id INTEGER NOT NULL,
    CONSTRAINT FK_Peca_2 FOREIGN KEY (fk_Lote_id) REFERENCES Lote (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Peca_3 FOREIGN KEY (fk_Categoria_id) REFERENCES Categoria (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Saida (
    id SERIAL PRIMARY KEY,
    data_saida DATE NOT NULL DEFAULT CURRENT_DATE,
    tipo VARCHAR(10),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    fk_Peca_id INTEGER NOT NULL,
    fk_Usuario_id INTEGER NOT NULL,
    CONSTRAINT FK_Saida_2 FOREIGN KEY (fk_Peca_id) REFERENCES Peca (id) ON DELETE CASCADE,
    CONSTRAINT FK_Saida_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE
);
