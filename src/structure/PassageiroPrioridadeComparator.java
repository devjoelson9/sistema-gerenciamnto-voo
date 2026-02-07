package structure;

import entity.PassageiroEntity;

import java.util.Comparator;

public class PassageiroPrioridadeComparator implements Comparator<PassageiroEntity> {

    @Override
    public int compare(PassageiroEntity p1, PassageiroEntity p2) {

        int prioridade = Integer.compare(p2.getPrioridade(), p1.getPrioridade());

        if (prioridade != 0) return prioridade;

        return Integer.compare(p1.getOrdemChegada(), p2.getOrdemChegada());
    }
}