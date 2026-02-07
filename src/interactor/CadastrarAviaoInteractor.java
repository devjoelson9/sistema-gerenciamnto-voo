package interactor;

import entity.AviaoEntity;
import repository.AviaoRepository;
import repository.HistoricoRepository;

public class CadastrarAviaoInteractor {

    private final AviaoRepository aviaoRepository;
    private final HistoricoRepository historicoRepository;

    public CadastrarAviaoInteractor(
            AviaoRepository aviaoRepository,
            HistoricoRepository historicoRepository) {

        this.aviaoRepository = aviaoRepository;
        this.historicoRepository = historicoRepository;
    }

    public boolean executar(String codigo, String modelo, int capacidade) {
        if (aviaoRepository.existe(codigo)) {
            return false;
        }

        if (capacidade <= 0) {
            return false;
        }

        AviaoEntity aviao = new AviaoEntity(codigo, modelo, capacidade);

        aviaoRepository.salvar(aviao);

        historicoRepository.registrar("Avião cadastrado: " + codigo);

        return true;
    }
}
