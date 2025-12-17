package game;

import java.util.Scanner;

import entities.classes.Classes;
import entities.player.Player;

public class PlayerAuth {

    private final Scanner scanner;
    private final Menus menus;

    public PlayerAuth() {
        this.scanner = new Scanner(System.in);
        this.menus = new Menus();
    }

    public void criarPersonagem() {

        String nome = menus.menuNomePersonagem();
        Classes classe = menus.menuEscolhaClasse();

        Player player = new Player(nome, classe);

        GameManager.salvarJogador(player);

        System.out.println("Jogador criado com sucesso!");
    }
}
