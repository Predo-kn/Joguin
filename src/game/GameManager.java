package game;

import data.loader.PlayerData;
import data.loader.ClassFactory;
import entities.player.Player;
import entities.classes.Classes;
import entities.itens.*;
import game.exceptions.GameException;

import java.util.Map;

public class GameManager {

    public static Player carregarJogadorCompleto(String nomeJogador) {

        Map<String, String> dados = PlayerData.carregarDadosCompletos(nomeJogador);

        String nomeJogadorArquivo = dados.getOrDefault("nome", nomeJogador);
        String nomeClasse = dados.getOrDefault("classe", "Knight");

        Classes classe = ClassFactory.criarClasse(nomeClasse);
        classe.setNome(nomeJogadorArquivo);

        if (dados.containsKey("vida")) classe.setVida(Double.parseDouble(dados.get("vida")));
        if (dados.containsKey("dano")) classe.setDano(Double.parseDouble(dados.get("dano")));
        if (dados.containsKey("escudo")) classe.setEscudo(Double.parseDouble(dados.get("escudo")));
        if (dados.containsKey("attackSpeed")) classe.setAttackSpeed(Double.parseDouble(dados.get("attackSpeed")));
        if (dados.containsKey("moveSpeed")) classe.setMoveSpeed(Double.parseDouble(dados.get("moveSpeed")));

        Player player = new Player(nomeJogadorArquivo, classe);

        if (dados.containsKey("nivel")) {
            player.setNivel(Integer.parseInt(dados.get("nivel")));
        }

        PlayerData.carregarBag(player, dados);
        PlayerData.carregarItemEquipado(classe, dados);

        System.out.println("Jogador " + nomeJogadorArquivo + " carregado com sucesso!");

        return player;
    }



    private static Item criarItemPorNome(String nomeItem) {

        return switch (nomeItem.toLowerCase()) {

            case "máscara sem identidade" -> new Mascara();
            case "olho vigilante" -> new OlhoVigilante();
            case "ossos contráteis" -> new OssosContrateis();
            case "pena umbrosa" -> new PenaUmbrosa();
            case "presa do lobo" -> new PresaDoLobo();
            case "voz do vazio" -> new VozDoVazio();

            default -> {
                System.out.println("Item desconhecido: " + nomeItem);
                yield null;
            }
        };
    }

    public static void salvarJogador(Player player) {
        PlayerData.salvarJogador(player);
    }


}
