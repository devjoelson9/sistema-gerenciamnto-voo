package entity;

public class PassageiroEntity {
    private String nome;
    private String documento;
    private int prioridade;
    private String voo;
    private int ordemChegada;

    public PassageiroEntity(String nome, String documento, int prioridade, String voo, int ordemChegada) {
        this.nome = nome;
        this.documento = documento;
        this.prioridade = prioridade;
        this.voo = voo;
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

    public String getVoo() {
        return voo;
    }

    public int getOrdemChegada() {
        return ordemChegada;
    }
}
