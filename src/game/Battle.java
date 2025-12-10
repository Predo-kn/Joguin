package game;

import entities.classes.Classes;
import entities.inimigos.Inimigo;
import entities.itens.Item;

public class Battle {
    private Classes player;
    private Inimigo enemy;
    private int turnCount;
    private boolean isPlayerTurn;
    private BattleState state;

    public enum BattleState {
        ONGOING,
        PLAYER_WIN,
        PLAYER_DEFEAT
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
    }

    public void executeTurn() {
        if (state != BattleState.ONGOING) {
            throw new IllegalStateException("A batalha já terminou com estado: " + state);
        }

        turnCount++;

        try {
            if (isPlayerTurn) {
                executePlayerTurn();
            } else {
                executeEnemyTurn();
            }

            checkBattleEnd();
            toggleTurn();
        } catch (Exception e) {
            throw new RuntimeException("Erro durante execução de turno: " + e.getMessage(), e);
        }
    }

    private void executePlayerTurn() {
        System.out.println("\n--- Turno do Jogador " + turnCount + " ---");
        System.out.println("Vida do Jogador: " + player.getVida() + " | Vida do Inimigo: " + enemy.getVida());
        
        player.atacar(enemy);
        
        if (enemy.getVida() <= 0) {
            state = BattleState.PLAYER_WIN;
        }
    }

    private void executeEnemyTurn() {
        System.out.println("\n--- Turno do Inimigo " + turnCount + " ---");
        System.out.println("Vida do Jogador: " + player.getVida() + " | Vida do Inimigo: " + enemy.getVida());
        
        enemy.atacar(player);
        
        if (player.getVida() <= 0) {
            state = BattleState.PLAYER_DEFEAT;
        }
    }

    private void checkBattleEnd() {
        if (player.getVida() <= 0) {
            state = BattleState.PLAYER_DEFEAT;
            System.out.println("\nDerrota! O jogador foi derrotado!");
        } else if (enemy.getVida() <= 0) {
            state = BattleState.PLAYER_WIN;
            System.out.println("\nVitória! Inimigo derrotado!");
        }
    }

    private void toggleTurn() {
        isPlayerTurn = !isPlayerTurn;
    }

    public void startAutoBattle() {
        System.out.println("\n=== Iniciando Batalha ===");
        System.out.println("Jogador: " + player.getNome() + " vs Inimigo");
        
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
                return drop;
            } else {
                System.out.println("Inimigo não deixou itens");
                return null;
            }
        }
        
        return null;
    }

    private void printBattleSummary() {
        System.out.println("\n=== Resumo da Batalha ===");
        System.out.println("Total de turnos: " + turnCount);
        System.out.println("Resultado: " + state);
        System.out.println("Vida do Jogador: " + player.getVida());
        System.out.println("Vida do Inimigo: " + enemy.getVida());
        System.out.println("=========================\n");
    }

    public Classes getPlayer() {
        return player;
    }

    public Inimigo getEnemy() {
        return enemy;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public BattleState getState() {
        return state;
    }
}
