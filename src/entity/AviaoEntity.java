package entity;

import structure.PassageiroPrioridadeComparator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AviaoEntity {
    private String codigo;
    private String modelo;
    private int capacidade;

    private Queue<PassageiroEntity> filaNormal = new LinkedList<>();
    private PriorityQueue<PassageiroEntity> filaPrioridade =
            new PriorityQueue<>(new PassageiroPrioridadeComparator());

    public AviaoEntity(String codigo, String modelo, int capacidade) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    public void adicionarPassageiro(PassageiroEntity p) {
        if (p.getPrioridade() > 0) {
            filaPrioridade.add(p);
        } else {
            filaNormal.add(p);
        }
    }

    public PassageiroEntity proximoPassageiro() {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.poll();
        }
        return filaNormal.poll();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
