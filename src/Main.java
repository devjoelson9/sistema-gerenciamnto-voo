import controller.SistemaController;
import repository.*;
import interactor.*;
import structure.PassageiroPrioridadeComparator;

public class Main {

    public static void main(String[] args) {

        var aviaoRepo = new AviaoRepository();
        var historicoRepo = new HistoricoRepository();
        var embarqueRepo = new EmbarqueRepository(new PassageiroPrioridadeComparator());

        var controller = new SistemaController(
                new CadastrarAviaoInteractor(aviaoRepo, historicoRepo),
                new RemoverAviaoInteractor(aviaoRepo, historicoRepo),
                new ListarAvioesInteractor(aviaoRepo),
                new VenderPassagemInteractor(embarqueRepo, historicoRepo),
                new EmbarcarInteractor(embarqueRepo, historicoRepo),
                new ListarHistoricoInteractor(historicoRepo),
                new DesfazerOperacaoInteractor(historicoRepo)
        );

        controller.iniciar();
    }
}
