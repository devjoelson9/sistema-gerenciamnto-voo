package controller;

import entity.AviaoEntity;
import entity.PassageiroEntity;
import entity.OperacaoEntity;
import interactor.*;

import java.util.List;
import java.util.Scanner;

public class SistemaController {

    private final Scanner scanner = new Scanner(System.in);

    private final CadastrarAviaoInteractor cadastrarAviao;
    private final RemoverAviaoInteractor removerAviao;
    private final ListarAvioesInteractor listarAvioes;
    private final VenderPassagemInteractor venderPassagem;
    private final EmbarcarInteractor embarcar;
    private final ListarHistoricoInteractor listarHistorico;
    private final DesfazerOperacaoInteractor desfazer;
    private final BuscarAviaoInteractor buscarAviao;

    public SistemaController(
            CadastrarAviaoInteractor cadastrarAviao,
            RemoverAviaoInteractor removerAviao,
            ListarAvioesInteractor listarAvioes,
            VenderPassagemInteractor venderPassagem,
            EmbarcarInteractor embarcar,
            ListarHistoricoInteractor listarHistorico,
            DesfazerOperacaoInteractor desfazer,
            BuscarAviaoInteractor buscarAviao
    ) {
        this.cadastrarAviao = cadastrarAviao;
        this.removerAviao = removerAviao;
        this.listarAvioes = listarAvioes;
        this.venderPassagem = venderPassagem;
        this.embarcar = embarcar;
        this.listarHistorico = listarHistorico;
        this.desfazer = desfazer;
        this.buscarAviao = buscarAviao;
    }

    /* ======================================================
       MÉTODOS AUXILIARES (CORREÇÃO DOS BUGS DE INPUT)
       ====================================================== */

    private int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Digite um número válido.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }

    private String lerString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = scanner.nextLine().trim();

            if (!s.isEmpty()) return s;

            System.out.println("Campo não pode ser vazio.");
        }
    }

    /* ======================================================
       MENU PRINCIPAL
       ====================================================== */

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n========================================");
            System.out.println("SISTEMA DE GESTÃO DE EMPRESA AÉREA");
            System.out.println("========================================");
            System.out.println("1 - Gerenciamento de Aviões");
            System.out.println("2 - Gestão de Passageiros e Embarque");
            System.out.println("3 - Histórico de Operações");
            System.out.println("0 - Encerrar Sistema");

            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> menuAvioes();
                case 2 -> menuPassageiros();
                case 3 -> menuHistorico();
            }

        } while (opcao != 0);
    }

    /* ======================================================
       AVIÕES
       ====================================================== */

    private void menuAvioes() {

        int op;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("GERENCIAMENTO DE AVIÕES");
            System.out.println("----------------------------------------");
            System.out.println("1 - Cadastrar novo avião");
            System.out.println("2 - Listar aviões cadastrados");
            System.out.println("3 - Remover avião");
            System.out.println("4 - Buscar avião");
            System.out.println("0 - Voltar");

            op = lerInt("Escolha: ");

            switch (op) {
                case 1 -> cadastrarAviao();
                case 2 -> listarAvioes();
                case 3 -> removerAviao();
                case 4 -> buscarAviao();
            }

        } while (op != 0);
    }

    private void cadastrarAviao() {

        System.out.println("\n=========== CADASTRAR AVIÃO ===========");

        String codigo = lerString("Código: ");
        String modelo = lerString("Modelo: ");
        int cap = lerInt("Capacidade: ");

        boolean sucesso = cadastrarAviao.executar(codigo, modelo, cap);

        System.out.println(sucesso ?
                "\n✔ Avião cadastrado com sucesso!" :
                "\n✖ Erro: código já cadastrado ou capacidade inválida!");
    }

    private void listarAvioes() {

        List<AviaoEntity> lista = listarAvioes.executar();

        System.out.println("\n================ LISTA DE AVIÕES ===============");

        if (lista.isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s%n", "Código", "Modelo", "Capacidade");
        System.out.println("------------------------------------------------");

        for (AviaoEntity a : lista) {
            System.out.printf("%-10s %-20s %-10d%n",
                    a.getCodigo(),
                    a.getModelo(),
                    a.getCapacidade());
        }
    }

    private void removerAviao() {

        System.out.println("\n=========== REMOVER AVIÃO ===========");

        String codigo = lerString("Informe o código: ");

        boolean ok = removerAviao.executar(codigo);

        System.out.println(ok ?
                "✔ Avião removido com sucesso!" :
                "✖ Avião não encontrado.");
    }

    private void buscarAviao() {

        System.out.println("\n=========== BUSCAR AVIÃO ===========");

        String codigo = lerString("Informe o código: ");

        try {
            AviaoEntity aviao = buscarAviao.executar(codigo);

            System.out.printf("""
                    ------------------------------------
                    Código     : %s
                    Modelo     : %s
                    Capacidade : %d
                    ------------------------------------
                    """,
                    aviao.getCodigo(),
                    aviao.getModelo(),
                    aviao.getCapacidade());

        } catch (IllegalArgumentException e) {
            System.out.println("✖ " + e.getMessage());
        }
    }

    /* ======================================================
       PASSAGEIROS
       ====================================================== */

    private void menuPassageiros() {

        int op;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("GESTÃO DE PASSAGEIROS E EMBARQUE");
            System.out.println("----------------------------------------");
            System.out.println("1 - Vender passagem");
            System.out.println("2 - Embarcar próximo passageiro");
            System.out.println("0 - Voltar");

            op = lerInt("Escolha: ");

            switch (op) {
                case 1 -> venderPassagem();
                case 2 -> embarcar();
            }

        } while (op != 0);
    }

    private void venderPassagem() {

        List<AviaoEntity> avioes = listarAvioes.executar();

        if (avioes.isEmpty()) {
            System.out.println("Nenhum voo disponível.");
            return;
        }

        for (int i = 0; i < avioes.size(); i++) {
            AviaoEntity a = avioes.get(i);
            System.out.println((i + 1) + " - " + a.getCodigo() + " | " + a.getModelo());
        }

        int escolha = lerInt("Escolha o voo: ") - 1;

        if (escolha < 0 || escolha >= avioes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        String nome = lerString("Nome do passageiro: ");
        String doc = lerString("Documento: ");
        int prioridade = lerInt("Prioridade (0 normal / >0 prioritário): ");

        venderPassagem.executar(nome, doc, avioes.get(escolha).getCodigo(), prioridade);

        System.out.println("✔ Passageiro adicionado à fila.");
    }

    private void embarcar() {

        System.out.println("\n=========== EMBARQUE ===========");

        String codigo = lerString("Digite o código do voo: ");

        PassageiroEntity p = embarcar.executar(codigo);

        System.out.println(p == null ?
                "Fila vazia." :
                "✔ Passageiro embarcado: " + p.getNome());
    }

    /* ======================================================
       HISTÓRICO
       ====================================================== */

    private void menuHistorico() {

        int op;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("HISTÓRICO DE OPERAÇÕES");
            System.out.println("----------------------------------------");
            System.out.println("1 - Exibir histórico");
            System.out.println("2 - Desfazer última operação");
            System.out.println("0 - Voltar");

            op = lerInt("Escolha: ");

            switch (op) {
                case 1 -> exibirHistorico();
                case 2 -> desfazer();
            }

        } while (op != 0);
    }

    private void exibirHistorico() {
        listarHistorico.executar()
                .forEach(op -> System.out.println("- " + op.getDescricao()));
    }

    private void desfazer() {
        OperacaoEntity op = desfazer.executar();

        System.out.println(op == null ?
                "Nada para desfazer." :
                "✔ Operação desfeita: " + op.getDescricao());
    }
}
