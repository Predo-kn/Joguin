package data.loader;

import game.exceptions.GameException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {
    
    private static final String CLASSES_DIR = "src/data/classes/";
    private static final String INIMIGOS_DIR = "src/data/inimigos/";
    private static final String PLAYERS_DIR = "src/data/players/";
    
    public static Map<String, Double> carregarClasse(String nomeClasse) {
        return carregarArquivo(CLASSES_DIR + nomeClasse + ".txt");
    }
    
    public static Map<String, Double> carregarInimigo(String nomeInimigo) {
        return carregarArquivo(INIMIGOS_DIR + nomeInimigo + ".txt");
    }
    
    public static Map<String, Double> carregarJogador(String nomeJogador) {
        return carregarArquivo(PLAYERS_DIR + nomeJogador + ".txt");
    }
    
    public static Map<String, Double> carregarArquivo(String caminhoArquivo) {
        Map<String, Double> dados = new HashMap<>();
        File arquivo = new File(caminhoArquivo);
        
        if (!arquivo.exists()) {
            throw new GameException("Arquivo não encontrado: " + caminhoArquivo);
        }
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }
                
                String[] partes = linha.split("=");
                if (partes.length == 2) {
                    String chave = partes[0].trim();
                    try {
                        double valor = Double.parseDouble(partes[1].trim());
                        dados.put(chave, valor);
                    } catch (NumberFormatException e) {
                        throw new GameException("Valor inválido no arquivo: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            throw new GameException("Erro ao ler arquivo: " + caminhoArquivo, e);
        }
        
        return dados;
    }
    
    public static void salvarDados(String caminhoArquivo, Map<String, Double> dados) {
        try {
            File arquivo = new File(caminhoArquivo);
            arquivo.getParentFile().mkdirs();
            
            java.io.FileWriter escritor = new java.io.FileWriter(arquivo);
            for (Map.Entry<String, Double> entrada : dados.entrySet()) {
                escritor.write(entrada.getKey() + "=" + entrada.getValue() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            throw new GameException("Erro ao salvar arquivo: " + caminhoArquivo, e);
        }
    }
    
    public static void salvarJogador(String nomeJogador, Map<String, Double> dados) {
        salvarDados(PLAYERS_DIR + nomeJogador + ".txt", dados);
    }
    
    public static boolean jogadorExiste(String nomeJogador) {
        return new File(PLAYERS_DIR + nomeJogador + ".txt").exists();
    }
}
