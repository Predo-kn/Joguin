package entities.inimigos;

import entities.classes.Classes;
import entities.itens.Item;

import java.util.Random;

public abstract sealed class Inimigo permits Carnical, CorvoSombrio, EcoAbismo,
        LarvaObservadora, LoboSilvestre, SemRosto, Aurelion {

    private double vida;
    private double dano;
    private double escudo;
    private double atackSpeed;
    private double moveSpeed;


    public Inimigo(double vida, double dano, double escudo, double atackSpeed,  double moveSpeed) {
        this.vida = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.atackSpeed = atackSpeed;
        this.moveSpeed = moveSpeed;
    }
    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getAtackSpeed() {
        return atackSpeed;
    }

    public void setAtackSpeed(double atackSpeed) {
        this.atackSpeed = atackSpeed;
    }

    public double getEscudo() {
        return escudo;
    }

    public void setEscudo(double escudo) {
        this.escudo = escudo;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void atacar(Classes classes){
        if(!classes.esquivar(this)){
            classes.setVida(classes.getVida() - (this.dano - classes.getEscudo()));
            System.out.println("Personagem recebeu dano");
        }else{
            System.out.println("Personagem esquivou");
        }
    }

    public double chanceEsquiva(Classes classes) {
        double diferenca = this.moveSpeed - classes.getAttackSpeed();
        if (diferenca <= 0) return 0;
        if (diferenca > 10) diferenca = 10;
        return diferenca * 10;
    }


    public boolean esquivar(Classes classes){
        double chanceEsquiva = chanceEsquiva(classes);
        double sorteio = Math.random();
        try{
            if(chanceEsquiva < 10){
                return false;
            }else{
                if(sorteio < (chanceEsquiva/100)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(Exception e){
            System.out.println("Erro ao esquivar");
        }
        return false;
    }

    public Item drop(Item[] itens){
        Random rd = new Random();

        if (rd.nextInt(0,1) == 1){
            return itens[0];
        }else {
            return itens[1];
        }
    }
}
