package entities.inimigos;

import entities.classes.Personagem;

public abstract sealed class Inimigo permits CervoDesalinhado, CordeiroDesfigurado, EcoAbismo, EscorpiaoDasCinzas,
EspiritoDunas, FragmentoErrante, Gargula, GuardiãoTempestades, LarvaObservadora, LoboDoVento, LoboSilvestre,
LodoRastejante, SapoInchado, SemRosto, TrepadeiraAnimada{

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

    public void atacar(Personagem personagem){
        if(!personagem.esquivar(this)){
            personagem.setVida(personagem.getVida() - (this.dano - personagem.getEscudo()));
            System.out.println("Personagem recebeu dano");
        }else{
            System.out.println("Personagem esquivou");
        }
    }

    public double chanceEsquiva(Personagem personagem) {
        double diferenca = this.moveSpeed - personagem.getAttackSpeed();
        if (diferenca <= 0) return 0;
        if (diferenca > 10) diferenca = 10;
        return diferenca * 10;
    }


    public boolean esquivar(Personagem personagem){
        double chanceEsquiva = chanceEsquiva(personagem);
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
