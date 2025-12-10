import entities.classes.Knight;
import entities.classes.Archer;
import entities.inimigos.Aurelion;
import entities.inimigos.Carnical;
import entities.itens.CoracaoDoAurelion;
import entities.player.Player;
import game.Battle;
import game.GameManager;
import data.loader.PlayerData;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Sistema de Persistência de Dados ===\n");
            
            System.out.println("--- Carregando Arquivo de Classe ---");
            Knight knight = new Knight();
            knight.setNome("Arthur");
            System.out.println("Knight carregado - Vida: " + knight.getVida() + 
                             ", Dano: " + knight.getDano());
            
            Archer archer = new Archer();
            System.out.println("Archer carregado - Vida: " + archer.getVida() + 
                             ", Dano: " + archer.getDano());
            
            System.out.println("\n--- Carregando Arquivos de Inimigos ---");
            Aurelion boss = new Aurelion();
            System.out.println("Aurelion carregado - Vida: " + boss.getVida() + 
                             ", Dano: " + boss.getDano());
            
            Carnical carnical = new Carnical();
            System.out.println("Carnical carregado - Vida: " + carnical.getVida() + 
                             ", Dano: " + carnical.getDano());
            
            CoracaoDoAurelion coracao = new CoracaoDoAurelion();
            boss.adicionarDrop(coracao);
            
            System.out.println("\n=== Iniciando Batalha ===");
            Battle battle = new Battle(knight, boss);
            battle.startAutoBattle();
            
            System.out.println("\n=== Salvando Dados do Jogador ===");
            GameManager.salvarJogador(knight);
            
            System.out.println("\n=== Carregando Jogador Salvo ===");
            Player jogadorCarregado = GameManager.carregarJogadorCompleto("Arthur");
            System.out.println("Jogador carregado: " + jogadorCarregado.getNome());
            System.out.println("Classe: " + jogadorCarregado.getClasse().getClass().getSimpleName());
            System.out.println("Vida: " + jogadorCarregado.getClasse().getVida());
            System.out.println("Item equipado: " + (jogadorCarregado.getClasse().getItemEquipado() != null ? 
                jogadorCarregado.getClasse().getItemEquipado().getNome() : "Nenhum"));
            
        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}