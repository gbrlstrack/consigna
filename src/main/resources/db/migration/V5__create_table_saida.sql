CREATE TABLE IF NOT EXISTS Saida (
    id SERIAL PRIMARY KEY,
    data_saida DATE NOT NULL DEFAULT CURRENT_DATE,
    observacao TEXT,
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    fk_Peca_id INTEGER NOT NULL,
    fk_Usuario_id INTEGER NOT NULL,
    CONSTRAINT FK_Saida_2 FOREIGN KEY (fk_Peca_id) REFERENCES Peca (id) ON DELETE CASCADE,
    CONSTRAINT FK_Saida_3 FOREIGN KEY (fk_Usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE
);