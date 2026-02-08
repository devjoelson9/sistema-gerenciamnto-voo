package interactor;

import entity.AviaoEntity;
import entity.PassageiroEntity;
import repository.AviaoRepository;
import repository.HistoricoRepository;

public class VenderPassagemInteractor {

    private final AviaoRepository aviaoRepository;
    private final HistoricoRepository historicoRepository;

    private int contadorChegada = 0;

    public VenderPassagemInteractor(
            AviaoRepository aviaoRepository,
            HistoricoRepository historicoRepository) {

        this.aviaoRepository = aviaoRepository;
        this.historicoRepository = historicoRepository;
    }

    public void executar(String nome, String documento, String codigoVoo, int prioridade) {

        AviaoEntity aviao = aviaoRepository.buscarPorCodigo(codigoVoo);

        if (aviao == null) {
            throw new IllegalArgumentException("Voo não encontrado");
        }

        int ordem = contadorChegada++;

        PassageiroEntity passageiro =
                new PassageiroEntity(nome, documento, prioridade, ordem);

        aviao.adicionarPassageiro(passageiro);

        if (prioridade > 0) {
            historicoRepository.registrar(
                    "Passagem PRIORITÁRIA vendida: " + nome +
                            " | voo: " + codigoVoo +
                            " | prioridade: " + prioridade
            );
        } else {
            historicoRepository.registrar(
                    "Passagem vendida: " + nome +
                            " | voo: " + codigoVoo
            );
        }
    }

}
