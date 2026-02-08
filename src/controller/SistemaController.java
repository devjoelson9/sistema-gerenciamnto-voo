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
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> menuAvioes();
                case 2 -> menuPassageiros();
                case 3 -> menuHistorico();
            }

        } while (opcao != 0);
    }

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
            System.out.print("Escolha: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> cadastrarAviao();
                case 2 -> listarAvioes();
                case 3 -> removerAviao();
                case 4 -> buscarAviao();
            }

        } while (op != 0);
    }

    private void buscarAviao() {

        System.out.println("\n=========== BUSCAR AVIÃO ===========");

        System.out.print("Informe o código do avião: ");
        String codigo = scanner.nextLine();

        try {
            AviaoEntity aviao = buscarAviao.executar(codigo);

            System.out.println("\n✔ Avião encontrado!");
            System.out.println("------------------------------------");
            System.out.println("Código     : " + aviao.getCodigo());
            System.out.println("Modelo     : " + aviao.getModelo());
            System.out.println("Capacidade : " + aviao.getCapacidade());
            System.out.println("------------------------------------");

        } catch (IllegalArgumentException e) {
            System.out.println("\n✖ " + e.getMessage());
        }
    }



    private void menuPassageiros() {

        int op;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("GESTÃO DE PASSAGEIROS E EMBARQUE");
            System.out.println("----------------------------------------");
            System.out.println("1 - Vender passagem");
            System.out.println("2 - Embarcar próximo passageiro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> venderPassagem();
                case 2 -> embarcar();
            }

        } while (op != 0);
    }

    private void menuHistorico() {

        int op;

        do {
            System.out.println("\n----------------------------------------");
            System.out.println("HISTÓRICO DE OPERAÇÕES");
            System.out.println("----------------------------------------");
            System.out.println("1 - Exibir histórico");
            System.out.println("2 - Desfazer última operação");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> exibirHistorico();
                case 2 -> desfazer();
            }

        } while (op != 0);
    }

    private void cadastrarAviao() {

        System.out.println("\n=========== CADASTRAR AVIÃO ===========");

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Capacidade: ");
        int cap = Integer.parseInt(scanner.nextLine());

        boolean sucesso = cadastrarAviao.executar(codigo, modelo, cap);

        if (sucesso) {
            System.out.println("\nAvião cadastrado com sucesso!");
        } else {
            System.out.println("\nErro: código já cadastrado ou capacidade inválida!");
        }
    }



    private void listarAvioes() {

        List<AviaoEntity> lista = listarAvioes.executar();

        System.out.println("\n================ LISTA DE AVIÕES ===============");

        if (lista.isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
            System.out.println("============================================\n");
            return;
        }

        System.out.printf("%-10s %-20s %-10s%n",
                "Código", "Modelo", "Capacidade");

        System.out.println("------------------------------------------------");

        for (AviaoEntity a : lista) {
            System.out.printf("%-10s %-20s %-10d%n",
                    a.getCodigo(),
                    a.getModelo(),
                    a.getCapacidade());
        }

        System.out.println("================================================\n");
    }


    private void removerAviao() {

        System.out.println("\n=========== REMOVER AVIÃO ===========");

        System.out.print("Informe o código: ");
        String codigo = scanner.nextLine();

        boolean ok = removerAviao.executar(codigo);

        if (ok)
            System.out.println("✔ Avião removido com sucesso!");
        else
            System.out.println("✖ Avião não encontrado.");

    }


    private void venderPassagem() {

        System.out.println("\n=========== VENDA DE PASSAGEM ===========");

        List<AviaoEntity> avioes = listarAvioes.executar();

        if (avioes.isEmpty()) {
            System.out.println("Nenhum voo disponível.");
            return;
        }

        System.out.println("\nVoos disponíveis:");
        for (int i = 0; i < avioes.size(); i++) {
            AviaoEntity a = avioes.get(i);
            System.out.println((i + 1) + " - " + a.getCodigo() + " | " + a.getModelo());
        }

        System.out.print("\nEscolha o voo: ");
        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= avioes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        AviaoEntity aviao = avioes.get(escolha);

        System.out.print("Nome do passageiro: ");
        String nome = scanner.nextLine();

        System.out.print("Documento: ");
        String doc = scanner.nextLine();

        System.out.print("Prioridade (0 normal / >0 prioritário): ");
        int prioridade = Integer.parseInt(scanner.nextLine());

        venderPassagem.executar(nome, doc, aviao.getCodigo(), prioridade);

        System.out.println("\n✔ Passageiro adicionado à fila do voo " + aviao.getCodigo());

    }



    private void embarcar() {

        System.out.println("\n=========== EMBARQUE ===========");

        System.out.print("Digite o código do voo: ");
        String codigo = scanner.nextLine();

        try {
            PassageiroEntity p = embarcar.executar(codigo);

            if (p == null) {
                System.out.println("Fila de embarque vazia.");
            } else {
                System.out.println("✔ Passageiro embarcado: " + p.getNome());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }



    private void exibirHistorico() {

        System.out.println("\n=========== HISTÓRICO DE OPERAÇÕES ===========");

        List<OperacaoEntity> lista = listarHistorico.executar();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma operação registrada.");
            return;
        }

        int i = 1;

        for (OperacaoEntity op : lista) {
            System.out.println(i++ + " - " + op.getDescricao());
        }

    }


    private void desfazer() {

        System.out.println("\n=========== DESFAZER OPERAÇÃO ===========");

        OperacaoEntity op = desfazer.executar();

        if (op == null)
            System.out.println("Nada para desfazer.");
        else
            System.out.println("✔ Operação desfeita: " + op.getDescricao());

    }

}
