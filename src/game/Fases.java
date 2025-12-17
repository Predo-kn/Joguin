package game;

import entities.itens.*;
import entities.player.Player;
import data.loader.PlayerData;

import entities.inimigos.*;

import java.util.ArrayList;

public class Fases {

    private ArrayList<Inimigo> inimigos;

    public void iniciarFase(Player player) {
        switch (player.getNivel()) {
            case 1 -> iniciarFase1(player);
            case 2 -> iniciarFase2(player);
            case 3 -> iniciarFase3(player);
            case 4 -> iniciarFase4(player);
            case 5 -> iniciarFase5(player);
            case 6 -> iniciarFase6(player);
            default -> System.out.println("Não há mais fases disponíveis.");
        }
    }


    public void iniciarFase1(Player player) {
        inimigos = new ArrayList<>();
        inimigos.add(new LoboSilvestre());
        inimigos.add(new LoboSilvestre());
        inimigos.add(new LoboSilvestre());

        boolean venceuTodos = executarFase(player, inimigos, new PresaDoLobo());

        if (venceuTodos) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            iniciarFase2(player);
        }
    }

    public void iniciarFase2(Player player) {
        inimigos = new ArrayList<>();
        inimigos.add(new CorvoSombrio());
        inimigos.add(new CorvoSombrio());
        inimigos.add(new CorvoSombrio());
        inimigos.add(new CorvoSombrio());

        boolean venceuTodos = executarFase(player, inimigos, new PenaUmbrosa());

        if (venceuTodos) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            iniciarFase3(player);
        }
    }

    public void iniciarFase3(Player player) {
        inimigos = new ArrayList<>();
        inimigos.add(new LarvaObservadora());
        inimigos.add(new LarvaObservadora());
        inimigos.add(new LarvaObservadora());

        boolean venceuTodos = executarFase(player, inimigos, new OlhoVigilante());

        if (venceuTodos) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            iniciarFase4(player);
        }
    }

    public void iniciarFase4(Player player) {
        inimigos = new ArrayList<>();
        inimigos.add(new Carnical());
        inimigos.add(new Carnical());

        boolean venceuTodos = executarFase(player, inimigos, new OssosContrateis());

        if (venceuTodos) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            iniciarFase5(player);
        }
    }

    public void iniciarFase5(Player player) {
        inimigos = new ArrayList<>();

        inimigos.add(new SemRosto());

        System.out.println("\nFASE 4 — MINI BOSS ");

        boolean venceuMiniBoss = executarFase(player, inimigos, new Mascara());

        if (venceuMiniBoss) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            System.out.println("Mini Boss derrotado!");
            iniciarFase6(player);
        }
    }

    public void iniciarFase6(Player player) {
        inimigos = new ArrayList<>();

        inimigos.add(new Aurelion());

        System.out.println("\nFASE 6 — BOSS FINAL ");

        boolean venceuBoss = executarFase(player, inimigos, new OlhoVigilante());

        if (venceuBoss) {
            player.getClasse().setVida(player.getClasse().getVidaMaxima());
            subirNivel(player);
            System.out.println(" PARABÉNS! Você derrotou o Boss Final!");
            System.out.println(" O mundo agora reconhece sua força.");
        }
    }


    private boolean executarFase(Player player, ArrayList<Inimigo> inimigos, entities.itens.Item drop) {
        for (Inimigo inimigo : inimigos) {
            inimigo.adicionarDrop(drop);

            System.out.println("\n Inimigo encontrado: " + inimigo.getClass().getSimpleName());
            Battle battle = new Battle(player.getClasse(), inimigo);
            battle.executeBattleAndGetDrop();

            if (battle.getState() != Battle.BattleState.PLAYER_WIN) {
                System.out.println("Você perdeu a batalha. Fase encerrada.");
                return false;
            }
        }
        return true;
    }

    private void subirNivel(Player player) {
        player.setNivel(player.getNivel() + 1);
        PlayerData.updateLevel(player.getNome(), player.getNivel());
        System.out.println(" Nível aumentado para " + player.getNivel());
    }
}
