package entities.player;

import data.repository.PlayerBag;
import entities.classes.Classes;

import java.util.ArrayList;

public class Player<T> {
    private String nome;
    private Classes classe;
    private PlayerBag bag;
    private int nivel;

    public Player(String nome){
        this.nome = nome;
        bag = new PlayerBag();
    }

    public Player(String nome, Classes classe){
        this.nome = nome;
        this.classe = classe;
        this.bag = new PlayerBag();
        this.nivel = 1;
        
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

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public int getNivel(){
        return this.nivel;
    }

}
