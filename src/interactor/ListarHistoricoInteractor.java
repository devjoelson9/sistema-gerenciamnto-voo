package interactor;

import entity.OperacaoEntity;
import repository.HistoricoRepository;

import java.util.List;

public class ListarHistoricoInteractor implements Interactor<List<OperacaoEntity>> {

    private final HistoricoRepository historicoRepository;

    public ListarHistoricoInteractor(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public List<OperacaoEntity> executar() {
        return historicoRepository.listar();
    }
}
