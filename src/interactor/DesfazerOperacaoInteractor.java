package interactor;


import entity.OperacaoEntity;
import repository.HistoricoRepository;

public class DesfazerOperacaoInteractor implements Interactor<OperacaoEntity>{
    private final HistoricoRepository historicoRepository;

    public DesfazerOperacaoInteractor(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public OperacaoEntity executar() {

        if (historicoRepository.vazio()) {
            return null;
        }

        return historicoRepository.desfazer();
    }
}
