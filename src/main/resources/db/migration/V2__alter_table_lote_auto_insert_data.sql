ALTER TABLE Lote
ALTER COLUMN data_entrada TYPE TIMESTAMP,
ALTER COLUMN data_entrada SET DEFAULT CURRENT_TIMESTAMP;
