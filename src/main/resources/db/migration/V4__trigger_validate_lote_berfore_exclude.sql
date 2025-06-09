CREATE OR REPLACE FUNCTION impedir_exclusao_lote_com_peca_ativa()
RETURNS TRIGGER AS $$
DECLARE
    pecas_invalidas INTEGER;
BEGIN
    -- Verifica se há peças com status diferente de 0 (vendida)
    SELECT COUNT(*) INTO pecas_invalidas
    FROM Peca
    WHERE fk_Lote_id = OLD.id AND status != 0;

    IF pecas_invalidas > 0 THEN
        RAISE EXCEPTION 'Não é permitido excluir o lote: contém peça(s) que não foram vendidas.';
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_verificar_pecas_antes_delete_lote ON lote;
CREATE TRIGGER trigger_verificar_pecas_antes_delete_lote
BEFORE DELETE ON Lote
FOR EACH ROW
EXECUTE FUNCTION impedir_exclusao_lote_com_peca_ativa();
