package game;

import data.loader.PlayerData;
import entities.classes.Classes;
import entities.inimigos.Inimigo;
import entities.itens.Item;
import entities.itens.Raridade;

import java.util.Scanner;

public class Battle {

    private final Classes player;
    private final Inimigo enemy;

    private int turnCount;
    private boolean isPlayerTurn;
    private BattleState state;

    private boolean playerDefending;
    private double escudoOriginal;

    private final Scanner sc = new Scanner(System.in);

    public enum BattleState {
        ONGOING,
        PLAYER_WIN,
        PLAYER_DEFEAT,
        FLED
    }

    public Battle(Classes player, Inimigo enemy) {
        if (player == null || enemy == null) {
            throw new IllegalArgumentException("Jogador e inimigo não podem ser nulos");
        }

        this.player = player;
        this.enemy = enemy;
        this.turnCount = 0;
        this.isPlayerTurn = true;
        this.state = BattleState.ONGOING;
        this.playerDefending = false;
    }


    public void startAutoBattle() {
        System.out.println("\n=== Iniciando Batalha ===");
        System.out.println(player.getNome() + " vs " + enemy.getClass().getSimpleName());

        while (state == BattleState.ONGOING) {
            executeTurn();
        }

        printBattleSummary();
    }

    public Item executeBattleAndGetDrop() {
        startAutoBattle();

        if (state == BattleState.PLAYER_WIN) {
            Item drop = enemy.soltarDrop();

            if (drop != null) {
                System.out.println("Item obtido: " + drop.getNome());
                player.getBag().addItem(drop, player);
            } else {
                System.out.println("Inimigo não deixou itens");
            }

            return drop;
        }

        return null;
    }

    private void executeTurn() {
        turnCount++;

        if (isPlayerTurn) {
            turnoJogador();
        } else {
            turnoInimigo();
        }

        verificarFim();
        isPlayerTurn = !isPlayerTurn;
    }

    private void turnoJogador() {
        System.out.println("\n--- Turno " + turnCount + " (Jogador) ---");
        exibirStatus();

        while (true) {
            System.out.println("1- Atacar | 2- Fugir | 3- Defender | 4- Ver Atributos | 5- Mochila");
            String escolha = sc.next();

            switch (escolha) {
                case "1" -> {
                    player.atacar(enemy);
                    return;
                }
                case "2" -> {
                    tentarFuga();
                    return;
                }
                case "3" -> {
                    defender();
                    return;
                }
                case "4" -> {
                    System.out.println(player.getClasse());
                }
                case "5" -> {
                    player.getBag().listarItens();
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void turnoInimigo() {
        System.out.println("\n--- Turno " + turnCount + " (Inimigo) ---");
        exibirStatus();

        enemy.atacar(player);

        if (playerDefending) {
            player.setEscudo(escudoOriginal);
            playerDefending = false;
        }
    }

    private void tentarFuga() {
        double diff = player.getMoveSpeed() - enemy.getMoveSpeed();
        double chance = Math.max(0, Math.min(10, diff)) * 10;

        if (Math.random() * 100 < chance) {
            System.out.println("Fuga bem sucedida!");
            state = BattleState.FLED;
        } else {
            System.out.println("Fuga falhou!");
        }
    }

    private void defender() {
        escudoOriginal = player.getEscudo();
        double bonus = Math.max(1, escudoOriginal * 0.5);

        player.setEscudo(escudoOriginal + bonus);
        playerDefending = true;

        System.out.println("Jogador se defende (+escudo temporário)");
    }

    private void verificarFim() {
        if (player.getVida() <= 0) {
            state = BattleState.PLAYER_DEFEAT;
            System.out.println("\nDerrota! O jogador foi derrotado.");
        } else if (enemy.getVida() <= 0) {
            state = BattleState.PLAYER_WIN;
            System.out.println("\nVitória! Inimigo derrotado.");
        }
        PlayerData playerdata = new PlayerData();

    }

    private void exibirStatus() {
        System.out.println(
                "Jogador HP: " + player.getVida() +
                        " | Inimigo HP: " + enemy.getVida()
        );
    }

    private void printBattleSummary() {
        System.out.println("\n=== Resumo da Batalha ===");
        System.out.println("Turnos: " + turnCount);
        System.out.println("Resultado: " + state);
        System.out.println("========================\n");
    }

    public BattleState getState() {
        return state;
    }

}
