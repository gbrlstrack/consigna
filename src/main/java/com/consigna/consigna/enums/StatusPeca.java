package com.consigna.consigna.enums;

public enum StatusPeca {
    CADASTRADO,        // Registrada, ainda não disponível
    ATIVO,             // Disponível para venda
    RESERVADO,         // Cliente reservou
    VENDIDO,           // Vendida, prazo de devolução em aberto
    VENDIDO_DEFINITIVO,// Venda consolidada
    EM_TROCA,          // Em processo de devolução/troca
    DEVOLVIDO_DONO,    // Devolvida ao dono
    RETIRADO_DONO,     // Dono retirou antes de vender
    INATIVO,           // Não disponível temporariamente
    CANCELADO          // Removida do sistema
}
