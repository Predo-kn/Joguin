package data.loader;

import game.exceptions.GameException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {

    private static final String BASE_DIR = "src/data/";
    private static final String CLASSES_DIR = BASE_DIR + "classes/";
    private static final String INIMIGOS_DIR = BASE_DIR + "inimigos/";
    private static final String PLAYERS_DIR = BASE_DIR + "players/";

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
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            throw new GameException("Arquivo não encontrado: " + caminhoArquivo);
        }

        Map<String, Double> dados = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                linha = linha.trim();

                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                String[] partes = linha.split("=", 2);
                if (partes.length != 2) {
                    throw new GameException(
                            "Formato inválido em " + caminhoArquivo + " linha " + numeroLinha + ": " + linha
                    );
                }

                String chave = partes[0].trim();
                String valorStr = partes[1].trim();

                try {
                    double valor = Double.parseDouble(valorStr);
                    dados.put(chave, valor);
                } catch (NumberFormatException e) {
                    throw new GameException(
                            "Valor inválido em " + caminhoArquivo + " linha " + numeroLinha + ": " + linha
                    );
                }
            }

        } catch (IOException e) {
            throw new GameException("Erro ao ler arquivo: " + caminhoArquivo, e);
        }

        return dados;
    }

    public static void salvarDados(String caminhoArquivo, Map<String, Double> dados) {
        File arquivo = new File(caminhoArquivo);
        File diretorio = arquivo.getParentFile();

        if (diretorio != null && !diretorio.exists()) {
            diretorio.mkdirs();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) {
            for (Map.Entry<String, Double> entrada : dados.entrySet()) {
                escritor.write(entrada.getKey() + "=" + entrada.getValue());
                escritor.newLine();
            }
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
