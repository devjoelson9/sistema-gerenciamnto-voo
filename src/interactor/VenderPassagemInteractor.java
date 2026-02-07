package interactor;

import entity.PassageiroEntity;
import repository.EmbarqueRepository;
import repository.HistoricoRepository;

public class VenderPassagemInteractor {

    private final EmbarqueRepository embarqueRepository;
    private final HistoricoRepository historicoRepository;

    private int contadorChegada = 0;

    public VenderPassagemInteractor(
            EmbarqueRepository embarqueRepository,
            HistoricoRepository historicoRepository) {

        this.embarqueRepository = embarqueRepository;
        this.historicoRepository = historicoRepository;
    }

    public void executar(String nome, String documento, String voo, int prioridade) {
        int ordem = contadorChegada++;

        PassageiroEntity passageiro = new PassageiroEntity(nome, documento, prioridade, voo, ordem);

        if (prioridade > 0) {

            embarqueRepository.adicionarPrioritario(passageiro);

            historicoRepository.registrar(
                    "Passagem PRIORITÁRIA vendida: " + nome +
                            " | prioridade: " + prioridade
            );

        } else {
            embarqueRepository.adicionarNormal(passageiro);

            historicoRepository.registrar(
                    "Passagem vendida: " + nome
            );
        }
    }
}
