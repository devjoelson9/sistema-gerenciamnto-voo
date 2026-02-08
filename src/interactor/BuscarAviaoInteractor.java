package interactor;

import entity.AviaoEntity;
import repository.AviaoRepository;

public class BuscarAviaoInteractor {
    private final AviaoRepository aviaoRepository;

    public BuscarAviaoInteractor(AviaoRepository aviaoRepository) {
        this.aviaoRepository = aviaoRepository;
    }

    public AviaoEntity executar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do avião inválido");
        }

        AviaoEntity aviao = aviaoRepository.buscarPorCodigo(codigo);

        if (aviao == null) {
            throw new IllegalArgumentException("Avião não encontrado");
        }

        return aviao;
    }

}
