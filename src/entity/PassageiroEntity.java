package entity;

public class PassageiroEntity {
    private String nome;
    private String documento;
    private int prioridade;
    private int ordemChegada;

    public PassageiroEntity(String nome, String documento, int prioridade, int ordemChegada) {
        this.nome = nome;
        this.documento = documento;
        this.prioridade = prioridade;
        this.ordemChegada = ordemChegada;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getOrdemChegada() {
        return ordemChegada;
    }
}
