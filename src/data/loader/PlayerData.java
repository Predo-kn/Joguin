package data.loader;

import entities.player.Player;
import entities.classes.Classes;
import game.exceptions.GameException;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    
    public static void salvarJogador(Player player) {
        if (player == null || player.getNome() == null) {
            throw new GameException("Jogador inválido para salvar");
        }
        
        Map<String, Double> dados = new HashMap<>();
        
        if (player.getClasse() != null) {
            Classes classe = player.getClasse();
            dados.put("vida", classe.getVida());
            dados.put("dano", classe.getDano());
            dados.put("escudo", classe.getEscudo());
            dados.put("attackSpeed", classe.getAttackSpeed());
            dados.put("moveSpeed", classe.getMoveSpeed());
        }
        
        DataLoader.salvarJogador(player.getNome(), dados);
        System.out.println("Jogador " + player.getNome() + " salvo com sucesso!");
    }
    
    public static Map<String, Double> carregarJogador(String nomeJogador) {
        return DataLoader.carregarJogador(nomeJogador);
    }
    
    public static boolean jogadorExiste(String nomeJogador) {
        return DataLoader.jogadorExiste(nomeJogador);
    }
}
