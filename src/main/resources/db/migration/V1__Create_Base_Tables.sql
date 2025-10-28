CREATE TABLE IF NOT EXISTS Consignatario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    telefone VARCHAR(14),
    tipo_documento VARCHAR(11),
    documento VARCHAR(14) NOT NULL
);

CREATE TABLE IF NOT EXISTS Categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Lote (
    id BIGSERIAL PRIMARY KEY,
    data_entrada DATE DEFAULT CURRENT_DATE,
    data_fechamento DATE,
    status VARCHAR(15),
    valor_total NUMERIC(10,2),
    fk_Consignatario_id BIGINT NOT NULL,
    fk_Usuario_id BIGINT NOT NULL,
    CONSTRAINT FK_Lote_2 FOREIGN KEY (fk_Consignatario_id) REFERENCES Consignatario (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Lote_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Peca (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    valor_minimo NUMERIC(10,2),
    status VARCHAR(15),
    qr_code VARCHAR(255),
    palavras_chave VARCHAR(50),
    valor_de_venda NUMERIC(10,2) NOT NULL,
    valor_de_repasse NUMERIC(10,2),
    data_alteracao_status DATE,
    quantidade INTEGER NOT NULL DEFAULT 1,
    fk_Lote_id BIGINT,
    fk_Categoria_id BIGINT,
    CONSTRAINT FK_Peca_2 FOREIGN KEY (fk_Lote_id) REFERENCES Lote (id) ON DELETE RESTRICT,
    CONSTRAINT FK_Peca_3 FOREIGN KEY (fk_Categoria_id) REFERENCES Categoria (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Saida (
    id BIGSERIAL PRIMARY KEY,
    data_saida DATE NOT NULL DEFAULT CURRENT_DATE,
    tipo VARCHAR(10),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    fk_Peca_id BIGINT NOT NULL,
    fk_Usuario_id BIGINT NOT NULL,
    CONSTRAINT FK_Saida_2 FOREIGN KEY (fk_Peca_id) REFERENCES Peca (id) ON DELETE CASCADE,
    CONSTRAINT FK_Saida_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE
);
