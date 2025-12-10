package entities.itens;

import entities.classes.Classes;
import game.exceptions.InvalidArgumentException;

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
        if (nome == null || nome.isEmpty()) {
            throw new InvalidArgumentException("Nome do item não pode ser nulo ou vazio");
        }
        if (mult < 0) {
            throw new InvalidArgumentException("Multiplicador do item não pode ser negativo");
        }
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
        if (mult < 0) {
            throw new InvalidArgumentException("Multiplicador do item não pode ser negativo");
        }
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

    public void aplicarEfeito(Classes cl) {
        if (cl == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }
        
        for (Buff b : buff) {
            aplicarBuff(cl, b);
        }
        
        System.out.println("Item " + nome + " aplicado com sucesso!");
    }

    private void aplicarBuff(Classes cl, Buff buffType) {
        double valor = mult;
        
        switch (buffType) {
            case VIDA:
                cl.setVida(cl.getVida() + valor);
                System.out.println("  +Vida: " + valor);
                break;
            case DANO:
                cl.setDano(cl.getDano() + valor);
                System.out.println("  +Dano: " + valor);
                break;
            case ESCUDO:
                cl.setEscudo(cl.getEscudo() + valor);
                System.out.println("  +Escudo: " + valor);
                break;
            case ATKSPEED:
                cl.setAttackSpeed(cl.getAttackSpeed() + valor);
                System.out.println("  +Atk Speed: " + valor);
                break;
            case MOVESPEED:
                cl.setMoveSpeed(cl.getMoveSpeed() + valor);
                System.out.println("  +Move Speed: " + valor);
                break;
        }
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
