package game;

import java.util.Scanner;

import data.loader.PlayerData;
import entities.classes.*;
import entities.player.Player;

public class Menus {

    private final Scanner sc = new Scanner(System.in);

    public void exibirMenuPrincipal() {
        int escolha;

        do {
            System.out.println("""
        ===============================================
        |   Bem-vindo ao Kingdom of the Forgotten!    |
        ===============================================
        Escolha uma opção para começar:
        1. Novo Jogo
        2. Carregar Jogo
        3. Remover Personagem
        4. Atualizar Nome do Personagem
        5. Sair
        ===============================================
                """);

            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> new PlayerAuth().criarPersonagem();
                case 2 -> menuCarregarJogo();
                case 3 -> menuRemoverJogador();
                case 4 -> menuAtualizarNomeJogador();
                case 5 -> System.out.println("Saindo do jogo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (escolha != 5);
    }


    public String menuNomePersonagem() {
        String nome;

        do {
            System.out.println("""
            ===============================================
            |           Criação de Novo Personagem        |
            ===============================================
            Digite o nome do personagem:
            ===============================================
                    """);

            nome = sc.nextLine();

            if (PlayerData.jogadorExiste(nome)) {
                System.out.println("Esse personagem já existe!");
            }

        } while (PlayerData.jogadorExiste(nome));

        return nome;
    }

    public Classes menuEscolhaClasse() {
        int escolha;

        do {
            System.out.println("""
            ===============================================
            |              Escolha sua Classe             |
            ===============================================
            1. Guerreiro
            2. Arqueiro
            3. Mago
            4. Tanque
            ===============================================
                    """);

            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1 -> { return new Knight(); }
                case 2 -> { return new Archer(); }
                case 3 -> { return new Mage(); }
                case 4 -> { return new Tanker(); }
                default -> System.out.println("Classe inválida!");
            }

        } while (escolha < 1 || escolha > 4);

        return null;
    }

    public void menuCarregarJogo() {
        System.out.println("""
        ===============================================
        |               Carregar Jogo                 |
        ===============================================
        Digite o nome do personagem:
        ===============================================
                """);

        String nome = sc.nextLine();

        if (PlayerData.jogadorExiste(nome)) {
            Player jogador = GameManager.carregarJogadorCompleto(nome);
            Fases fases = new Fases();
            fases.iniciarFase(jogador);
        } else {
            System.out.println("Personagem não encontrado.");
        }
    }

    public void menuRemoverJogador() {
        System.out.println("""
        ===============================================
        |             Remover Personagem              |
        ===============================================
        Digite o nome do personagem:
        ===============================================
                """);

        String nome = sc.nextLine();

        if (!PlayerData.jogadorExiste(nome)) {
            System.out.println("Personagem não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja remover? (Sim/Nao): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("Sim")) {
            PlayerData.removerJogador(nome);
        } else {
            System.out.println("Remoção cancelada.");
        }


    }

    public void menuAtualizarNomeJogador() {
        System.out.println("""
    ===============================================
    |          Atualizar Nome do Personagem       |
    ===============================================
    Digite o nome atual do personagem:
    ===============================================
            """);

        String nomeAntigo = sc.nextLine();

        if (!PlayerData.jogadorExiste(nomeAntigo)) {
            System.out.println("Personagem não encontrado.");
            return;
        }

        System.out.println("""
    ===============================================
    Digite o novo nome do personagem:
    ===============================================
            """);

        String nomeNovo = sc.nextLine();

        if (PlayerData.jogadorExiste(nomeNovo)) {
            System.out.println("Já existe um personagem com esse nome.");
            return;
        }

        System.out.print("Confirmar alteração? (Sim/Nao): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("Sim")) {
            PlayerData.atualizarNomeJogador(nomeAntigo, nomeNovo);
        } else {
            System.out.println("Alteração cancelada.");
        }
    }

}
