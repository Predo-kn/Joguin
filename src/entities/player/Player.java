package entities.player;

import data.repository.PlayerBag;
import entities.classes.Classes;

import java.util.ArrayList;

public class Player<T> {
    private String nome;
    private Classes classe;
    private PlayerBag bag;

    public Player(String nome, Classes classe){
        this.nome = nome;
        this.classe = classe;
        this.bag = new PlayerBag();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public PlayerBag getBag() {
        return bag;
    }

    public void setBag(PlayerBag bag) {
        this.bag = bag;
    }
}
