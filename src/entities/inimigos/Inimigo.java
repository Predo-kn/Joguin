package entities.inimigos;

import entities.classes.Classes;
import entities.itens.Item;
import game.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Random;

public abstract sealed class Inimigo permits Carnical, CorvoSombrio, EcoAbismo,
        LarvaObservadora, LoboSilvestre, SemRosto, Aurelion {

    private double vida;
    private double dano;
    private double escudo;
    private double atackSpeed;
    private double moveSpeed;
    private ArrayList<Item> dropTable;

    public Inimigo(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        if (vida <= 0) throw new InvalidArgumentException("Vida do inimigo deve ser maior que 0");
        if (dano < 0) throw new InvalidArgumentException("Dano do inimigo não pode ser negativo");
        if (escudo < 0) throw new InvalidArgumentException("Escudo do inimigo não pode ser negativo");
        if (atackSpeed < 0) throw new InvalidArgumentException("Attack Speed do inimigo não pode ser negativo");
        if (moveSpeed < 0) throw new InvalidArgumentException("Move Speed do inimigo não pode ser negativo");
        
        this.vida = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.atackSpeed = atackSpeed;
        this.moveSpeed = moveSpeed;
        this.dropTable = new ArrayList<>();
    }
    
    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public double getAtackSpeed() {
        return atackSpeed;
    }

    public void setAtackSpeed(double atackSpeed) {
        if (atackSpeed < 0) {
            throw new InvalidArgumentException("Attack Speed não pode ser negativo");
        }
        this.atackSpeed = atackSpeed;
    }

    public double getEscudo() {
        return escudo;
    }

    public void setEscudo(double escudo) {
        if (escudo < 0) {
            throw new InvalidArgumentException("Escudo não pode ser negativo");
        }
        this.escudo = escudo;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        if (dano < 0) {
            throw new InvalidArgumentException("Dano não pode ser negativo");
        }
        this.dano = dano;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        if (moveSpeed < 0) {
            throw new InvalidArgumentException("Move Speed não pode ser negativo");
        }
        this.moveSpeed = moveSpeed;
    }

    public ArrayList<Item> getDropTable() {
        return dropTable;
    }

    public void adicionarDrop(Item item) {
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }
        dropTable.add(item);
    }

    public void adicionarDrops(Item... itens) {
        if (itens == null || itens.length == 0) {
            throw new InvalidArgumentException("Lista de itens não pode ser nula ou vazia");
        }
        for (Item item : itens) {
            if (item != null) {
                dropTable.add(item);
            }
        }
    }

    public Item soltarDrop() {
        if (dropTable.isEmpty()) {
            return null;
        }
        Random rd = new Random();
        int indice = rd.nextInt(dropTable.size());
        return dropTable.get(indice);
    }

    public void atacar(Classes classes){
        if (classes == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }
        
        if(!classes.esquivar(this)){
            classes.setVida(classes.getVida() - (this.dano - classes.getEscudo()));
            System.out.println("Personagem recebeu dano");
        }else{
            System.out.println("Personagem esquivou");
        }
    }

    public double chanceEsquiva(Classes classes) {
        if (classes == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }
        
        double diferenca = this.moveSpeed - classes.getAttackSpeed();
        if (diferenca <= 0) return 0;
        if (diferenca > 10) diferenca = 10;
        return diferenca * 10;
    }

    public boolean esquivar(Classes classes){
        if (classes == null) {
            throw new InvalidArgumentException("Personagem não pode ser nulo");
        }
        
        double chanceEsquiva = chanceEsquiva(classes);
        double sorteio = Math.random();
        
        if(chanceEsquiva < 10){
            return false;
        }else{
            return sorteio < (chanceEsquiva/100);
        }
    }
}
