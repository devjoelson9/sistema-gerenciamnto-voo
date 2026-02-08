import controller.SistemaController;
import repository.*;
import interactor.*;

public class Main {

    public static void main(String[] args) {

        var aviaoRepo = new AviaoRepository();
        var historicoRepo = new HistoricoRepository();

        var controller = new SistemaController(
                new CadastrarAviaoInteractor(aviaoRepo, historicoRepo),
                new RemoverAviaoInteractor(aviaoRepo, historicoRepo),
                new ListarAvioesInteractor(aviaoRepo),
                new VenderPassagemInteractor(aviaoRepo, historicoRepo),
                new EmbarcarInteractor(aviaoRepo, historicoRepo),
                new ListarHistoricoInteractor(historicoRepo),
                new DesfazerOperacaoInteractor(historicoRepo),
                new BuscarAviaoInteractor(aviaoRepo)
        );

        controller.iniciar();
    }
}
