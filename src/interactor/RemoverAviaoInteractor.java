package interactor;

import entity.AviaoEntity;
import repository.AviaoRepository;
import repository.HistoricoRepository;

public class RemoverAviaoInteractor{

    private final AviaoRepository aviaoRepository;
    private final HistoricoRepository historicoRepository;

    public RemoverAviaoInteractor(
            AviaoRepository aviaoRepository,
            HistoricoRepository historicoRepository) {

        this.aviaoRepository = aviaoRepository;
        this.historicoRepository = historicoRepository;
    }

    public boolean executar(String codigo) {
        AviaoEntity aviao = aviaoRepository.buscarPorCodigo(codigo);

        if (aviao == null) {
            return false;
        }
       
        boolean removido = aviaoRepository.removerPorCodigo(codigo);

        if (removido) {
            historicoRepository.registrar(
                    "Avião removido: " + aviao.getCodigo()
                            + " | Modelo: " + aviao.getModelo()
            );
        }

        return removido;
    }
}

