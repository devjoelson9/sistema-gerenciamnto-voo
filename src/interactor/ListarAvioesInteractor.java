package interactor;

import entity.AviaoEntity;
import repository.AviaoRepository;

import java.util.List;

public class ListarAvioesInteractor implements Interactor<List<AviaoEntity>> {

    private final AviaoRepository aviaoRepository;

    public ListarAvioesInteractor(AviaoRepository aviaoRepository) {
        this.aviaoRepository = aviaoRepository;
    }

    @Override
    public List<AviaoEntity> executar() {
        return aviaoRepository.listar();
    }
}
