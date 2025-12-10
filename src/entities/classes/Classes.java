package entities.classes;

import data.repository.PlayerBag;
import entities.inimigos.Inimigo;
import entities.itens.Item;
import game.exceptions.InvalidArgumentException;

import java.util.ArrayList;

public abstract sealed class Classes<T> extends Player permits Archer, Knight, Mage, Tanker {
    private double vida;
    private double dano;
    private double escudo;
    private double attackSpeed;
    private double moveSpeed;
    private ArrayList<T> itens;
    private Item itemEquipado;

    public Classes(String nome, double vida, double dano, double escudo, double attackSpeed, double moveSpeed){
        super(nome);
        super.setClasse(this);
        
        if (vida <= 0) throw new InvalidArgumentException("Vida deve ser maior que 0");
        if (dano < 0) throw new InvalidArgumentException("Dano não pode ser negativo");
        if (escudo < 0) throw new InvalidArgumentException("Escudo não pode ser negativo");
        if (attackSpeed < 0) throw new InvalidArgumentException("Attack Speed não pode ser negativo");
        if (moveSpeed < 0) throw new InvalidArgumentException("Move Speed não pode ser negativo");
        
        this.vida = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.itens = new ArrayList<>();
        this.itemEquipado = null;
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

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        if (dano < 0) {
            throw new InvalidArgumentException("Dano não pode ser negativo");
        }
        this.dano = dano;
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

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        if (attackSpeed < 0) {
            throw new InvalidArgumentException("Attack Speed não pode ser negativo");
        }
        this.attackSpeed = attackSpeed;
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

    public Item getItemEquipado() {
        return itemEquipado;
    }

    public void atacar(Inimigo inimigo){
        if (inimigo == null) {
            throw new InvalidArgumentException("Inimigo não pode ser nulo");
        }
        
        if(!inimigo.esquivar(this)){
            inimigo.setVida(inimigo.getVida() - (this.dano -  inimigo.getEscudo()));
            System.out.println("Inimigo recebeu dano");
        }else{
            System.out.println("Inimigo esquivou");
        }
    }

    public double chanceEsquiva(Inimigo inimigo) {
        if (inimigo == null) {
            throw new InvalidArgumentException("Inimigo não pode ser nulo");
        }
        
        double diferenca = this.moveSpeed - inimigo.getAtackSpeed();
        if (diferenca <= 0) return 0;
        if (diferenca > 10) diferenca = 10;
        return diferenca * 10;
    }


    public boolean esquivar(Inimigo inimigo){
        if (inimigo == null) {
            throw new InvalidArgumentException("Inimigo não pode ser nulo");
        }
        
        double chanceEsquiva = chanceEsquiva(inimigo);
        double sorteio = Math.random();
        
        if(chanceEsquiva < 10){
            return false;
        }else{
            return sorteio < (chanceEsquiva/100);
        }
    }

    public void equiparItem(Item item) {
        if (item == null) {
            throw new InvalidArgumentException("Item não pode ser nulo");
        }
        
        this.itemEquipado = item;
        item.aplicarEfeito(this);
    }

}
