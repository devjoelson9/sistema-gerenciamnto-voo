package repository;

import entity.OperacaoEntity;
import structure.Pilha;

import java.util.List;

public class HistoricoRepository {

    private final Pilha<OperacaoEntity> pilha = new Pilha<>();

    public void registrar(String descricao) {
        pilha.push(new OperacaoEntity(descricao));
    }

    public OperacaoEntity desfazer() {
        return pilha.pop();
    }

    public OperacaoEntity ultima() {
        return pilha.peek();
    }

    public List<OperacaoEntity> listar() {
        return pilha.listar();
    }


    public boolean vazio() {
        return pilha.isEmpty();
    }

    public int total() {
        return pilha.size();
    }
}
