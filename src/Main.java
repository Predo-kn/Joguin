import entities.classes.Knight;
import entities.inimigos.Aurelion;
import entities.itens.CoracaoDoAurelion;
import game.Battle;

public class Main {
    public static void main(String[] args) {
        try {
            Knight knight = new Knight(55, 12, 4, 2, 2);
            knight.setNome("Sir Arthur");
            
            Aurelion boss = new Aurelion();
            
            CoracaoDoAurelion coracao = new CoracaoDoAurelion();
            boss.adicionarDrop(coracao);
            
            Battle battle = new Battle(knight, boss);
            battle.executeBattleAndGetDrop();
            
        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}