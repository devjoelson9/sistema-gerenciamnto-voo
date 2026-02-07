package interactor;

import entity.PassageiroEntity;
import repository.EmbarqueRepository;
import repository.HistoricoRepository;

public class EmbarcarInteractor implements Interactor<PassageiroEntity>{

    private final EmbarqueRepository embarqueRepository;
    private final HistoricoRepository historicoRepository;

    public EmbarcarInteractor(
            EmbarqueRepository embarqueRepository,
            HistoricoRepository historicoRepository) {

        this.embarqueRepository = embarqueRepository;
        this.historicoRepository = historicoRepository;
    }

    public PassageiroEntity executar() {

        PassageiroEntity passageiro = embarqueRepository.proximo();

        if (passageiro == null) {
            return null;
        }

        historicoRepository.registrar(
                "Embarque realizado: " +
                        passageiro.getNome() +
                        " | Prioridade: " + passageiro.getPrioridade()
        );

        return passageiro;
    }

}

