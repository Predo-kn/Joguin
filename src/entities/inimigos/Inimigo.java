package entities.inimigos;

import data.loader.DataLoader;
import entities.classes.Classes;
import entities.itens.Item;
import game.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public abstract sealed class Inimigo
        permits Carnical, CorvoSombrio, EcoAbismo,
        LarvaObservadora, LoboSilvestre, SemRosto, Aurelion {

    private double vida;
    private double dano;
    private double escudo;
    private double attackSpeed;
    private double moveSpeed;

    private final ArrayList<Item> dropTable;

    public Inimigo(double vida,
                   double dano,
                   double escudo,
                   double attackSpeed,
                   double moveSpeed) {

        if (vida <= 0) throw new InvalidArgumentException("Vida do inimigo deve ser maior que 0");
        if (dano < 0) throw new InvalidArgumentException("Dano do inimigo não pode ser negativo");
        if (escudo < 0) throw new InvalidArgumentException("Escudo do inimigo não pode ser negativo");
        if (attackSpeed < 0) throw new InvalidArgumentException("Attack Speed do inimigo não pode ser negativo");
        if (moveSpeed < 0) throw new InvalidArgumentException("Move Speed do inimigo não pode ser negativo");

        this.vida = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.dropTable = new ArrayList<>();
    }


    public double getVida() {
        return vida;
    }

    public double getDano() {
        return dano;
    }

    public double getEscudo() {
        return escudo;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }


    public void setVida(double vida) {
        this.vida = Math.max(0, vida);
    }

    public void setDano(double dano) {
        if (dano < 0) throw new InvalidArgumentException("Dano do inimigo não pode ser negativo");
        this.dano = dano;
    }

    public void setEscudo(double escudo) {
        if (escudo < 0) throw new InvalidArgumentException("Escudo do inimigo não pode ser negativo");
        this.escudo = escudo;
    }

    public void setAttackSpeed(double attackSpeed) {
        if (attackSpeed < 0) throw new InvalidArgumentException("Attack Speed do inimigo não pode ser negativo");
        this.attackSpeed = attackSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        if (moveSpeed < 0) throw new InvalidArgumentException("Move Speed do inimigo não pode ser negativo");
        this.moveSpeed = moveSpeed;
    }


    public boolean esquivar(Classes player) {
        double diferenca = player.getMoveSpeed() - this.attackSpeed;

        if (diferenca <= 0) return false;

        double chance = Math.min(diferenca, 10) * 10;
        return Math.random() < (chance / 100);
    }

    public void atacar(Classes player) {
        if (player == null)
            throw new InvalidArgumentException("Jogador não pode ser nulo");

        if (!player.esquivar(this)) {
            double danoFinal = Math.max(0, this.dano - player.getEscudo());
            player.setVida(player.getVida() - danoFinal);

            System.out.println("Jogador recebeu " + danoFinal + " de dano");
        } else {
            System.out.println("Jogador esquivou");
        }
    }



    public void adicionarDrop(Item item) {
        if (item == null) throw new InvalidArgumentException("Item de drop não pode ser nulo");
        dropTable.add(item);
    }

    public Item soltarDrop() {
        if (dropTable.isEmpty()) return null;

        int indice = new Random().nextInt(dropTable.size());
        return dropTable.remove(indice);
    }


    protected void carregarAtributosDoArquivo(String nomeArquivo) {
        try {
            Map<String, Double> dados = DataLoader.carregarInimigo(nomeArquivo);

            if (dados.containsKey("vida")) this.vida = dados.get("vida");
            if (dados.containsKey("dano")) this.dano = dados.get("dano");
            if (dados.containsKey("escudo")) this.escudo = dados.get("escudo");
            if (dados.containsKey("attackSpeed")) this.attackSpeed = dados.get("attackSpeed");
            if (dados.containsKey("moveSpeed")) this.moveSpeed = dados.get("moveSpeed");

        } catch (Exception e) {
            System.err.println("Erro ao carregar inimigo: " + e.getMessage());
        }
    }
}
