import entities.classes.Knight;
import entities.inimigos.Aurelion;
import game.Battle;

public class Main {
    public static void main(String[] args) {
        // Exemplo de uso do novo sistema de combate
        
        try {
            // Criar um knight (jogador)
            Knight knight = new Knight(55, 12, 4, 2, 2);
            knight.setNome("Sir Arthur");
            
            // Criar um inimigo (Aurelion)
            Aurelion boss = new Aurelion();
            
            // Criar uma batalha
            Battle battle = new Battle(knight, boss);
            
            // Executar a batalha automaticamente
            battle.startAutoBattle();
            
        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}