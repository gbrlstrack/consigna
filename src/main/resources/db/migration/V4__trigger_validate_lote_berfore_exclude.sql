CREATE OR REPLACE FUNCTION public.impedir_exclusao_lote_com_peca_ativa()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
DECLARE
    pecas_ativas_no_lote INTEGER;
BEGIN

    SELECT COUNT(*) INTO pecas_ativas_no_lote
    FROM Peca
    WHERE fk_Lote_id = OLD.id AND status = 'ATIVO';

    IF pecas_ativas_no_lote > 0 THEN
        RAISE EXCEPTION 'Não é permitido excluir o lote: ele contém peça(s) com status ATIVO.';
    END IF;

    RETURN OLD;
END;
$function$
;