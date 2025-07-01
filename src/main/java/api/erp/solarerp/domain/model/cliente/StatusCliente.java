package api.erp.solarerp.domain.model.cliente;

import lombok.Getter;

@Getter
public enum StatusCliente {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PENDENTE("Pendente"), // Ex: Aguardando aprovação ou documentação
    PROSPECT("Em Negociacao"); // Ex: Cliente potencial, ainda não efetivado

    private final String descricao;

    StatusCliente(String descricao) {
        this.descricao = descricao;
    }
}
