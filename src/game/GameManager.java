package game;

import data.loader.PlayerData;
import data.loader.ClassFactory;
import entities.player.Player;
import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.CoracaoDoAurelion;
import entities.itens.Mascara;
import entities.itens.OlhoVigilante;
import entities.itens.OssosContrateis;
import entities.itens.PenaUmbrosa;
import entities.itens.PresaDoLobo;
import entities.itens.VozDoVazio;
import entities.itens.Buff;
import entities.itens.Raridade;
import game.exceptions.GameException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    
    public static Player carregarJogadorCompleto(String nomeJogador) {
        Map<String, String> dados = PlayerData.carregarDadosCompletos(nomeJogador);
        
        String nomeJogadorArquivo = dados.getOrDefault("nome", nomeJogador);
        String nomeClasse = dados.getOrDefault("classe", "Knight");
        
        Classes classe = ClassFactory.criarClasse(nomeClasse);
        classe.setNome(nomeJogadorArquivo);
        
        if (dados.containsKey("vida")) {
            classe.setVida(Double.parseDouble(dados.get("vida")));
        }
        if (dados.containsKey("dano")) {
            classe.setDano(Double.parseDouble(dados.get("dano")));
        }
        if (dados.containsKey("escudo")) {
            classe.setEscudo(Double.parseDouble(dados.get("escudo")));
        }
        if (dados.containsKey("attackSpeed")) {
            classe.setAttackSpeed(Double.parseDouble(dados.get("attackSpeed")));
        }
        if (dados.containsKey("moveSpeed")) {
            classe.setMoveSpeed(Double.parseDouble(dados.get("moveSpeed")));
        }
        
        Player player = new Player(nomeJogadorArquivo, classe);
        
        if (dados.containsKey("totalItens")) {
            int totalItens = Integer.parseInt(dados.get("totalItens"));
            for (int i = 0; i < totalItens; i++) {
                String nomeItem = dados.getOrDefault("item_" + i + "_nome", null);
                if (nomeItem != null) {
                    String raridade = dados.getOrDefault("item_" + i + "_raridade", "COMMON");
                    double mult = Double.parseDouble(dados.getOrDefault("item_" + i + "_mult", "0"));
                    
                    Item item = criarItem(nomeItem, raridade, mult);
                    if (item != null) {
                        player.getBag().addItem(item);
                    }
                }
            }
        }
        
        if (dados.containsKey("itemEquipado")) {
            String nomeItemEquipado = dados.get("itemEquipado");
            String raridade = dados.getOrDefault("itemRaridade", "COMMON");
            double mult = Double.parseDouble(dados.getOrDefault("itemMult", "0"));
            
            Item itemEquipado = criarItem(nomeItemEquipado, raridade, mult);
            if (itemEquipado != null) {
                classe.equiparItem(itemEquipado);
            }
        }
        
        System.out.println("Jogador " + nomeJogador + " carregado com sucesso!");
        return player;
    }
    
    private static Item criarItem(String nomeItem, String raridade, double mult) {
        Item item = null;
        
        switch (nomeItem.toLowerCase()) {
            case "coração do aurelion":
                item = new CoracaoDoAurelion();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "máscara sem identidade":
                item = new Mascara();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "olho vigilante":
                item = new OlhoVigilante();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "ossos contráteis":
                item = new OssosContrateis();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "pena umbrosa":
                item = new PenaUmbrosa();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "presa do lobo":
                item = new PresaDoLobo();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            case "voz do vazio":
                item = new VozDoVazio();
                item.setMult(mult);
                item.setRaridade(Raridade.valueOf(raridade));
                break;
            default:
                System.out.println("Item desconhecido: " + nomeItem);
        }
        
        return item;
    }
    
    public static void salvarJogador(Player player) {
        PlayerData.salvarJogador(player);
    }
}
