package entities.classes;

import entities.inimigos.Inimigo;

import java.util.ArrayList;

public abstract sealed class Personagem <T> permits Guerreiro, Mago, Escudeiro{
    private double vida;
    private double dano;
    private double escudo;
    private double attackSpeed;
    private double moveSpeed;
    private ArrayList<T> itens;

    public Personagem(double vida, double dano, double escudo, double attackSpeed, double moveSpeed){
        this.vida = vida;
        this.dano = dano;
        this.escudo = escudo;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.itens = new ArrayList<>();
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public double getEscudo() {
        return escudo;
    }

    public void setEscudo(double escudo) {
        this.escudo = escudo;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void atacar(Inimigo inimigo){
        if(!inimigo.esquivar(this)){
            inimigo.setVida(inimigo.getVida() - (this.dano -  inimigo.getEscudo()));
            System.out.println("Inimigo recebeu dano");
        }else{
            System.out.println("Inimigo esquivou");
        }
    }

    public double chanceEsquiva(Inimigo inimigo) {
        double diferenca = this.moveSpeed - inimigo.getAtackSpeed();
        if (diferenca <= 0) return 0;
        if (diferenca > 10) diferenca = 10;
        return diferenca * 10;
    }


    public boolean esquivar(Inimigo inimigo){
        double chanceEsquiva = chanceEsquiva(inimigo);
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


}
