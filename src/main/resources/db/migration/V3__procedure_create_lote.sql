CREATE OR REPLACE FUNCTION public.inserir_lote_com_pecas(
    p_data_entrada date,
    p_data_fechamento date,
    p_status integer,
    p_valor_total numeric,
    p_fk_consignatario_id integer,
    p_fk_usuario_id integer,
    p_pecas jsonb
)
RETURNS integer
LANGUAGE plpgsql
AS $function$
DECLARE
    novo_lote_id INTEGER;
    pecas_inseridas INTEGER := 0;
    peca jsonb;
BEGIN
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
        p_fk_consignatario_id,
        p_fk_usuario_id
    )
    RETURNING id INTO novo_lote_id;

    FOR peca IN SELECT * FROM jsonb_array_elements(p_pecas)
    LOOP
        INSERT INTO Peca (
            nome,
            valor_solicitado,
            valor_minimo,
            status,
            palavras_chave,
            valor_de_venda,
            quantidade,
            fk_Lote_id,
            fk_Categoria_id
        ) VALUES (
            peca->>'nome',
            (peca->>'valor_solicitado')::NUMERIC,
            (peca->>'valor_minimo')::NUMERIC,
            (peca->>'status')::INTEGER,
            peca->>'palavras_chave',
            CASE WHEN peca ? 'valor_de_venda' THEN (peca->>'valor_de_venda')::NUMERIC ELSE NULL END,
            (peca->>'quantidade')::INTEGER,
            novo_lote_id,
            (peca->>'fk_Categoria_id')::INTEGER
        );

        pecas_inseridas := pecas_inseridas + 1;
    END LOOP;

    IF pecas_inseridas < 1 THEN
        RAISE EXCEPTION 'Não é possível criar lote sem nenhuma peça.';
    END IF;

    RETURN novo_lote_id;
END;
$function$;
