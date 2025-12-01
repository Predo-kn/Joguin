package entities.inimigos.bosses;

import entities.classes.Classes;

public class Aurelion {
    // Aurelion, o Guardião Sereno - Mapa 1 //

    private double vida = 100;
    private double dano = 12;
    private double escudo = 5;
    private double atackSpeed = 2;
    private double moveSpeed = 2;


    public Aurelion(double vida, double dano, double escudo, double atackSpeed,  double moveSpeed) {
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
}
