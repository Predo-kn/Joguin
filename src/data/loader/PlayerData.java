package data.loader;

import entities.player.Player;
import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.Buff;
import entities.itens.Raridade;
import game.exceptions.GameException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private static final String PLAYERS_DIR = "src/data/players/";

    public static void salvarJogador(Player player) {
        if (player == null || player.getNome() == null || player.getNome().isBlank()) {
            throw new GameException("Jogador inválido para salvar");
        }

        Map<String, String> dados;

        if (jogadorExiste(player.getNome())) {
            dados = carregarDadosCompletos(player.getNome());
        } else {
            dados = new HashMap<>();
        }

        dados.put("nome", player.getNome());
        dados.put("nivel", String.valueOf(player.getNivel()));

        salvarClasse(player, dados);
        salvarBag(player, dados);

        salvarDadosCompletos(player.getNome(), dados);
        System.out.println("Jogador " + player.getNome() + " salvo com sucesso!");
    }


    private static void salvarClasse(Player player, Map<String, String> dados) {
        Classes classe = player.getClasse();
        if (classe == null) return;

        dados.put("classe", classe.getClass().getSimpleName());
        dados.put("vida", String.valueOf(classe.getVida()));
        dados.put("dano", String.valueOf(classe.getDano()));
        dados.put("escudo", String.valueOf(classe.getEscudo()));
        dados.put("attackSpeed", String.valueOf(classe.getAttackSpeed()));
        dados.put("moveSpeed", String.valueOf(classe.getMoveSpeed()));

        if (classe.getItemEquipado() != null) {
            salvarItem(dados, "itemEquipado", classe.getItemEquipado(), -1);
        }
    }


    private static void salvarBag(Player player, Map<String, String> dados) {
        if (player.getBag() == null) return;

        dados.keySet().removeIf(k -> k.startsWith("item_"));
        dados.remove("totalItens");

        int count = 0;

        for (Item item : player.getBag().getItens()) {
            if (item == null) continue;

            salvarItem(dados, "item_" + count, item, item.getRaridade().ordinal());
            count++;
        }

        dados.put("totalItens", String.valueOf(count));
    }


    private static void salvarItem(
            Map<String, String> dados,
            String prefixo,
            Item item,
            int slotRaridade
    ) {
        if (item == null) return;

        dados.put(prefixo + "_nome", item.getNome());
        dados.put(prefixo + "_raridade", item.getRaridade().name());
        dados.put(prefixo + "_slot", String.valueOf(slotRaridade));

        Map<Buff, Double> buffs = item.getBuffs();
        dados.put(prefixo + "_buffCount", String.valueOf(buffs.size()));

        int i = 0;
        for (Map.Entry<Buff, Double> entry : buffs.entrySet()) {
            dados.put(prefixo + "_buff_" + i,
                    entry.getKey().name() + ":" + entry.getValue());
            i++;
        }
    }

    private static Item criarItemDoTxt(Map<String, String> dados, String prefixo) {

        String nome = dados.get(prefixo + "_nome");
        Raridade raridade = Raridade.valueOf(dados.get(prefixo + "_raridade"));
        int buffCount = Integer.parseInt(dados.get(prefixo + "_buffCount"));

        Item item;

        switch (nome) {
            case "Máscara Sem Identidade" -> item = new entities.itens.Mascara();
            case "Olho Vigilante" -> item = new entities.itens.OlhoVigilante();

            default -> throw new GameException("Item desconhecido: " + nome);
        }

        item.setRaridade(raridade);

        for (int i = 0; i < buffCount; i++) {
            String[] partes = dados.get(prefixo + "_buff_" + i).split(":");
            Buff buff = Buff.valueOf(partes[0]);
            double valor = Double.parseDouble(partes[1]);
            item.addBuff(buff, valor);
        }

        return item;
    }



    public static void carregarBag(Player player, Map<String, String> dados) {

        int i = 0;

        while (dados.containsKey("item_" + i + "_nome")) {

            String base = "item_" + i;
            Item item = criarItemDoTxt(dados, base);

            player.getBag().addItem(item, player.getClasse());
            i++;
        }
    }



    public static void carregarItemEquipado(Classes classe, Map<String, String> dados) {

        if (!dados.containsKey("itemEquipado_nome")) return;

        Item item = criarItemDoTxt(dados, "itemEquipado");
        classe.equiparItem(item);
    }



    private static void salvarDadosCompletos(String nomeJogador, Map<String, String> dados) {
        String caminho = PLAYERS_DIR + nomeJogador + ".txt";
        File arquivo = new File(caminho);
        arquivo.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Map.Entry<String, String> entry : dados.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new GameException("Erro ao salvar jogador: " + caminho, e);
        }
    }

    public static Map<String, String> carregarDadosCompletos(String nomeJogador) {
        String caminho = PLAYERS_DIR + nomeJogador + ".txt";
        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            throw new GameException("Arquivo do jogador não encontrado: " + caminho);
        }

        Map<String, String> dados = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                String[] partes = linha.split("=", 2);
                if (partes.length == 2) {
                    dados.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            throw new GameException("Erro ao ler jogador: " + caminho, e);
        }

        return dados;
    }

    public static boolean jogadorExiste(String nomeJogador) {
        return new File(PLAYERS_DIR + nomeJogador + ".txt").exists();
    }

    public static void updateLevel(String nomeJogador, int novoNivel) {
        if (novoNivel < 1) {
            throw new GameException("Nível inválido");
        }

        Map<String, String> dados = carregarDadosCompletos(nomeJogador);
        dados.put("nivel", String.valueOf(novoNivel));
        salvarDadosCompletos(nomeJogador, dados);
    }

    public static void removerJogador(String nomeJogador) {
        if (nomeJogador == null || nomeJogador.isBlank()) {
            throw new GameException("Nome do jogador inválido");
        }

        String caminho = PLAYERS_DIR + nomeJogador + ".txt";
        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            throw new GameException("Jogador não encontrado para remoção: " + nomeJogador);
        }

        if (!arquivo.delete()) {
            throw new GameException("Falha ao remover o jogador: " + nomeJogador);
        }

        System.out.println("Jogador " + nomeJogador + " removido com sucesso!");
    }

    public static void atualizarNomeJogador(String nomeAntigo, String nomeNovo) {
        if (!jogadorExiste(nomeAntigo)) {
            throw new GameException("Jogador não encontrado");
        }

        if (jogadorExiste(nomeNovo)) {
            throw new GameException("Já existe um jogador com esse nome");
        }

        Map<String, String> dados = carregarDadosCompletos(nomeAntigo);

        dados.put("nome", nomeNovo);

        salvarDadosCompletos(nomeNovo, dados);

        File arquivoAntigo = new File(PLAYERS_DIR + nomeAntigo + ".txt");
        if (!arquivoAntigo.delete()) {
            throw new GameException("Erro ao remover arquivo antigo do jogador");
        }

        System.out.println("Nome do personagem atualizado com sucesso!");
    }




}
