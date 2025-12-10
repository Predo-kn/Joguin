import entities.classes.Knight;
import entities.classes.Archer;
import entities.inimigos.Aurelion;
import entities.inimigos.Carnical;
import entities.itens.CoracaoDoAurelion;
import game.Battle;
import data.loader.PlayerData;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Carregando dados de arquivo ===\n");
            
            Knight knight = new Knight();
            knight.setNome("Arthur");
            System.out.println("Knight carregado - Vida: " + knight.getVida() + 
                             ", Dano: " + knight.getDano());
            
            Archer archer = new Archer();
            System.out.println("Archer carregado - Vida: " + archer.getVida() + 
                             ", Dano: " + archer.getDano());
            
            System.out.println("\nInimigos:");
            Aurelion boss = new Aurelion();
            System.out.println("Aurelion carregado - Vida: " + boss.getVida() + 
                             ", Dano: " + boss.getDano());
            
            Carnical carnical = new Carnical();
            System.out.println("Carnical carregado - Vida: " + carnical.getVida() + 
                             ", Dano: " + carnical.getDano());
            
            CoracaoDoAurelion coracao = new CoracaoDoAurelion();
            boss.adicionarDrop(coracao);
            
            System.out.println("\n=== Iniciando batalha ===");
            Battle battle = new Battle(knight, boss);
            battle.startAutoBattle();
            
            PlayerData.salvarJogador(knight);
            
        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}