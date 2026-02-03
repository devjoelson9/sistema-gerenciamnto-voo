package entity;

import java.time.LocalDateTime;

public class OperacaoEntity {
    private String descricao;
    private LocalDateTime dataHora;

    public OperacaoEntity(String descricao) {
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
