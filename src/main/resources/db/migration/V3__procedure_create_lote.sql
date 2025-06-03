CREATE OR REPLACE FUNCTION inserir_lote_com_pecas(
    p_data_entrada DATE,
    p_data_fechamento DATE,
    p_status INTEGER,
    p_valor_total NUMERIC(10,2),
    p_fk_Consignatario_id INTEGER,
    p_fk_Usuario_id INTEGER,
    p_pecas JSON -- Lista de peças, cada peça com nome, valor_solicitado, valor_minimo, status, palavras_chave, valor_de_venda, fk_Categoria_id
) RETURNS INTEGER AS $$
DECLARE
    novo_lote_id INTEGER;
    pecas_inseridas INTEGER := 0;
    peca JSON;
BEGIN
    -- Inserir lote
    INSERT INTO Lote (
        data_entrada,
        data_fechamento,
        status,
        valor_total,
        fk_Consignatario_id,
        fk_Usuario_id
    ) VALUES (
        p_data_entrada,
        p_data_fechamento,
        p_status,
        p_valor_total,
        p_fk_Consignatario_id,
        p_fk_Usuario_id
    )
    RETURNING id INTO novo_lote_id;

    -- Inserir peças
    FOREACH peca IN ARRAY (SELECT json_array_elements(p_pecas)) LOOP
        INSERT INTO Peca (
            nome,
            valor_solicitado,
            valor_minimo,
            status,
            palavras_chave,
            valor_de_venda,
            fk_Lote_id,
            fk_Categoria_id
        ) VALUES (
            peca->>'nome',
            (peca->>'valor_solicitado')::NUMERIC,
            (peca->>'valor_minimo')::NUMERIC,
            (peca->>'status')::INTEGER,
            peca->>'palavras_chave',
            CASE WHEN peca ? 'valor_de_venda' THEN (peca->>'valor_de_venda')::NUMERIC ELSE NULL END,
            novo_lote_id,
            (peca->>'fk_Categoria_id')::INTEGER
        );

        pecas_inseridas := pecas_inseridas + 1;
    END LOOP;

    -- Verifica se inseriu ao menos uma peça
    IF pecas_inseridas < 1 THEN
        RAISE EXCEPTION 'Não é possível criar lote sem nenhuma peça.';
    END IF;

    RETURN novo_lote_id;
END;
$$ LANGUAGE plpgsql;
