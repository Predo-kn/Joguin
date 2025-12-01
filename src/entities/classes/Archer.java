package entities.classes;

public non-sealed class Archer extends Classes {
    //Habilidades
    //• Flecha Dupla – 2 hits de 7 dano (14 total)
    //• Recuo Ágil – deixa ataque inimigo falhar (1 vez), cooldown 3
    //• Mira Mortal – +30% dano no próximo ataque

    private double vida = 45;
    private double dano = 10;
    private double escudo = 2;
    private double atackSpeed = 3;
    private double moveSpeed = 4;

    public Archer(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super(vida, dano, escudo, attackSpeed, moveSpeed);
    }


}
