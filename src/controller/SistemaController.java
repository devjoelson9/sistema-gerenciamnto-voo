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

    public SistemaController(
            CadastrarAviaoInteractor cadastrarAviao,
            RemoverAviaoInteractor removerAviao,
            ListarAvioesInteractor listarAvioes,
            VenderPassagemInteractor venderPassagem,
            EmbarcarInteractor embarcar,
            ListarHistoricoInteractor listarHistorico,
            DesfazerOperacaoInteractor desfazer
    ) {
        this.cadastrarAviao = cadastrarAviao;
        this.removerAviao = removerAviao;
        this.listarAvioes = listarAvioes;
        this.venderPassagem = venderPassagem;
        this.embarcar = embarcar;
        this.listarHistorico = listarHistorico;
        this.desfazer = desfazer;
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
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> cadastrarAviao();
                case 2 -> listarAvioes();
                case 3 -> removerAviao();
            }

        } while (op != 0);
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
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Capacidade: ");
        int cap = Integer.parseInt(scanner.nextLine());

        cadastrarAviao.executar(codigo, modelo, cap);
        System.out.println("Avião cadastrado!");
    }

    private void listarAvioes() {

        List<AviaoEntity> lista = listarAvioes.executar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
            return;
        }

        for (AviaoEntity a : lista) {
            System.out.println(a.getCodigo() + " | " + a.getModelo() + " | Cap: " + a.getCapacidadeMaxima());
        }
    }

    private void removerAviao() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        boolean ok = removerAviao.executar(codigo);

        System.out.println(ok ? "Removido!" : "Não encontrado.");
    }

    private void venderPassagem() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Documento: ");
        String doc = scanner.nextLine();

        System.out.print("Voo: ");
        String voo = scanner.nextLine();

        System.out.print("Prioridade (0 normal / >0 prioritário): ");
        int prioridade = Integer.parseInt(scanner.nextLine());

        venderPassagem.executar(nome, doc, voo, prioridade);

        System.out.println("Passagem vendida!");
    }

    private void embarcar() {

        PassageiroEntity p = embarcar.executar();

        if (p == null)
            System.out.println("Fila vazia.");
        else
            System.out.println("Embarcou: " + p.getNome());
    }

    private void exibirHistorico() {

        List<OperacaoEntity> lista = listarHistorico.executar();

        if (lista.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }

        for (OperacaoEntity op : lista) {
            System.out.println(op.getDescricao());
        }
    }

    private void desfazer() {

        OperacaoEntity op = desfazer.executar();

        if (op == null)
            System.out.println("Nada para desfazer.");
        else
            System.out.println("Desfeito: " + op.getDescricao());
    }
}
