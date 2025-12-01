package entities.itens;

import entities.classes.Classes;

import java.util.ArrayList;

public abstract class Item {
    private String nome;
    private String info;
    private Raridade raridade;
    private ArrayList<Buff> buff;
    private double mult;

    public Item(){

    }

    public Item(String nome, String info, Raridade raridade, double mult ) {
        this.nome = nome;
        this.info = info;
        this.raridade = raridade;
        this.buff = new ArrayList<>();
        this.mult = mult;
    }



    public double getMult() {
        return mult;
    }

    public void setMult(double mult) {
        this.mult = mult;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }


    public ArrayList<Buff> getBuff() {
        return buff;
    }

    public void setBuff(ArrayList<Buff> buff) {
        this.buff = buff;
    }

    public void setAtt(Classes cl){

    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", info='" + info + '\'' +
                ", raridade=" + raridade +
                '}';
    }
}
