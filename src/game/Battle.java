package game;

import entities.classes.Classes;
import entities.inimigos.Inimigo;

/**
 * Sistema de combate baseado em turnos.
 * Gerencia a lógica de batalha entre um jogador e um inimigo.
 */
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

    /**
     * Executa um turno de batalha
     */
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

    /**
     * Executa o turno do jogador
     */
    private void executePlayerTurn() {
        System.out.println("\n--- Turno do Jogador " + turnCount + " ---");
        System.out.println("Vida do Jogador: " + player.getVida() + " | Vida do Inimigo: " + enemy.getVida());
        
        player.atacar(enemy);
        
        if (enemy.getVida() <= 0) {
            state = BattleState.PLAYER_WIN;
        }
    }

    /**
     * Executa o turno do inimigo
     */
    private void executeEnemyTurn() {
        System.out.println("\n--- Turno do Inimigo " + turnCount + " ---");
        System.out.println("Vida do Jogador: " + player.getVida() + " | Vida do Inimigo: " + enemy.getVida());
        
        enemy.atacar(player);
        
        if (player.getVida() <= 0) {
            state = BattleState.PLAYER_DEFEAT;
        }
    }

    /**
     * Verifica se a batalha terminou
     */
    private void checkBattleEnd() {
        if (player.getVida() <= 0) {
            state = BattleState.PLAYER_DEFEAT;
            System.out.println("\n🪦 DERROTA! O jogador foi derrotado!");
        } else if (enemy.getVida() <= 0) {
            state = BattleState.PLAYER_WIN;
            System.out.println("\n🎉 VITÓRIA! Inimigo derrotado!");
        }
    }

    /**
     * Alterna entre turno do jogador e do inimigo
     */
    private void toggleTurn() {
        isPlayerTurn = !isPlayerTurn;
    }

    /**
     * Inicia a batalha automática até o final
     */
    public void startAutoBattle() {
        System.out.println("\n⚔️ INICIANDO BATALHA ⚔️");
        System.out.println("Jogador: " + player.getNome() + " vs Inimigo");
        
        while (state == BattleState.ONGOING) {
            executeTurn();
        }
        
        printBattleSummary();
    }

    /**
     * Imprime um resumo da batalha
     */
    private void printBattleSummary() {
        System.out.println("\n======== RESUMO DA BATALHA ========");
        System.out.println("Total de turnos: " + turnCount);
        System.out.println("Resultado: " + state);
        System.out.println("Vida do Jogador: " + player.getVida());
        System.out.println("Vida do Inimigo: " + enemy.getVida());
        System.out.println("====================================\n");
    }

    // Getters

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
