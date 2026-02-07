package repository;

import entity.PassageiroEntity;
import structure.Fila;
import structure.FilaPrioridade;
import java.util.Comparator;

public class EmbarqueRepository {

    private final Fila<PassageiroEntity> filaNormal = new Fila<>();
    private final FilaPrioridade<PassageiroEntity> filaPrioridade;

    public EmbarqueRepository(Comparator<PassageiroEntity> comparator) {
        this.filaPrioridade = new FilaPrioridade<>(comparator);
    }

    public void adicionarNormal(PassageiroEntity passageiro) {
        filaNormal.enqueue(passageiro);
    }

    public void adicionarPrioritario(PassageiroEntity passageiro) {
        filaPrioridade.add(passageiro);
    }


    public PassageiroEntity proximo() {
        if (!filaPrioridade.isEmpty()) {
            return filaPrioridade.poll();
        }
        return filaNormal.dequeue();
    }

    public int totalNormal() {
        return filaNormal.size();
    }

    public int totalPrioridade() {
        return filaPrioridade.size();
    }

    public boolean vazio() {
        return filaNormal.isEmpty() && filaPrioridade.isEmpty();
    }
}

