package interactor;

import entity.AviaoEntity;
import entity.PassageiroEntity;
import repository.AviaoRepository;
import repository.HistoricoRepository;

public class EmbarcarInteractor {

    private final AviaoRepository aviaoRepository;
    private final HistoricoRepository historicoRepository;

    public EmbarcarInteractor(
            AviaoRepository aviaoRepository,
            HistoricoRepository historicoRepository) {

        this.aviaoRepository = aviaoRepository;
        this.historicoRepository = historicoRepository;
    }

    public PassageiroEntity executar(String codigoVoo) {

        AviaoEntity aviao = aviaoRepository.buscarPorCodigo(codigoVoo);

        if (aviao == null) {
            throw new IllegalArgumentException("Voo não encontrado");
        }

        PassageiroEntity passageiro = aviao.proximoPassageiro();

        if (passageiro == null) {
            return null;
        }

        historicoRepository.registrar(
                "Embarque realizado: " +
                        passageiro.getNome() +
                        " | voo: " + codigoVoo +
                        " | prioridade: " + passageiro.getPrioridade()
        );

        return passageiro;
    }

}
