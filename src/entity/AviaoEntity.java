package entity;

public class AviaoEntity {
    private String codigo;
    private String modelo;
    private int capacidadeMaxima;

    public AviaoEntity(String codigo, String modelo, int capacidadeMaxima) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}
