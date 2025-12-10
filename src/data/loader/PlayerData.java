package data.loader;

import entities.player.Player;
import entities.classes.Classes;
import entities.itens.Item;
import data.repository.PlayerBag;
import game.exceptions.GameException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    
    public static void salvarJogador(Player player) {
        if (player == null || player.getNome() == null) {
            throw new GameException("Jogador inválido para salvar");
        }
        
        Map<String, String> dados = new HashMap<>();
        
        dados.put("nome", player.getNome());
        
        if (player.getClasse() != null) {
            Classes classe = player.getClasse();
            dados.put("classe", obterNomeClasse(classe));
            dados.put("vida", String.valueOf(classe.getVida()));
            dados.put("dano", String.valueOf(classe.getDano()));
            dados.put("escudo", String.valueOf(classe.getEscudo()));
            dados.put("attackSpeed", String.valueOf(classe.getAttackSpeed()));
            dados.put("moveSpeed", String.valueOf(classe.getMoveSpeed()));
            
            if (classe.getItemEquipado() != null) {
                Item item = classe.getItemEquipado();
                dados.put("itemEquipado", item.getNome());
                dados.put("itemRaridade", item.getRaridade().toString());
                dados.put("itemMult", String.valueOf(item.getMult()));
            }
        }
        
        if (player.getBag() != null && !player.getBag().getItens().isEmpty()) {
            ArrayList<Item> itens = player.getBag().getItens();
            dados.put("totalItens", String.valueOf(itens.size()));
            
            for (int i = 0; i < itens.size(); i++) {
                Item item = itens.get(i);
                if (item != null) {
                    dados.put("item_" + i + "_nome", item.getNome());
                    dados.put("item_" + i + "_raridade", item.getRaridade().toString());
                    dados.put("item_" + i + "_mult", String.valueOf(item.getMult()));
                }
            }
        }
        
        salvarDadosCompletos(player.getNome(), dados);
        System.out.println("Jogador " + player.getNome() + " salvo com sucesso!");
    }
    
    private static void salvarDadosCompletos(String nomeJogador, Map<String, String> dados) {
        String caminhoArquivo = "src/data/players/" + nomeJogador + ".txt";
        try {
            java.io.File arquivo = new java.io.File(caminhoArquivo);
            arquivo.getParentFile().mkdirs();
            
            java.io.FileWriter escritor = new java.io.FileWriter(arquivo);
            for (Map.Entry<String, String> entrada : dados.entrySet()) {
                escritor.write(entrada.getKey() + "=" + entrada.getValue() + "\n");
            }
            escritor.close();
        } catch (java.io.IOException e) {
            throw new GameException("Erro ao salvar jogador: " + caminhoArquivo, e);
        }
    }
    
    public static Map<String, String> carregarDadosCompletos(String nomeJogador) {
        String caminhoArquivo = "src/data/players/" + nomeJogador + ".txt";
        Map<String, String> dados = new HashMap<>();
        java.io.File arquivo = new java.io.File(caminhoArquivo);
        
        if (!arquivo.exists()) {
            throw new GameException("Arquivo do jogador não encontrado: " + caminhoArquivo);
        }
        
        try (java.io.BufferedReader leitor = new java.io.BufferedReader(new java.io.FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }
                
                String[] partes = linha.split("=", 2);
                if (partes.length == 2) {
                    dados.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (java.io.IOException e) {
            throw new GameException("Erro ao ler arquivo do jogador: " + caminhoArquivo, e);
        }
        
        return dados;
    }
    
    public static Map<String, Double> carregarJogador(String nomeJogador) {
        return DataLoader.carregarJogador(nomeJogador);
    }
    
    public static boolean jogadorExiste(String nomeJogador) {
        return DataLoader.jogadorExiste(nomeJogador);
    }
    
    private static String obterNomeClasse(Classes classe) {
        String nomeCompleto = classe.getClass().getName();
        return nomeCompleto.substring(nomeCompleto.lastIndexOf('.') + 1);
    }
}
