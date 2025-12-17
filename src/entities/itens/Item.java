package entities.itens;

import entities.classes.Classes;
import game.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

    private String nome;
    private String info;
    private Raridade raridade;
    private Map<Buff, Double> buffs;

    public Item() {
        this.buffs = new HashMap<>();
    }

    public Item(String nome, String info, Raridade raridade) {
        if (nome == null || nome.isEmpty()) {
            throw new InvalidArgumentException("Nome do item não pode ser nulo ou vazio");
        }

        this.nome = nome;
        this.info = info;
        this.raridade = raridade;
        this.buffs = new HashMap<>();
    }

    public Map<Buff, Double> getBuffs() {
        return buffs;
    }


    public void addBuff(Buff buff, double valor) {
        if (valor <= 0) {
            throw new InvalidArgumentException("Valor do buff deve ser positivo");
        }
        buffs.put(buff, valor);
    }

    public void aplicarEfeito(Classes cl) {
        if (cl == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }

        for (Map.Entry<Buff, Double> entry : buffs.entrySet()) {
            Buff tipo = entry.getKey();
            double valor = entry.getValue();

            switch (tipo) {
                case VIDA -> cl.setVida(cl.getVida() + valor);
                case DANO -> cl.setDano(cl.getDano() + valor);
                case ESCUDO -> cl.setEscudo(cl.getEscudo() + valor);
                case ATKSPEED -> cl.setAttackSpeed(cl.getAttackSpeed() + valor);
                case MOVESPEED -> cl.setMoveSpeed(cl.getMoveSpeed() + valor);
            }

            System.out.println("  +" + tipo + ": " + valor);
        }

        System.out.println("Item " + nome + " aplicado com sucesso!");
    }

    public String getNome() {
        return nome;
    }

    public String getInfo() {
        return info;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public void setBuffs(Map<Buff, Double> buffs) {
        this.buffs = buffs;
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
