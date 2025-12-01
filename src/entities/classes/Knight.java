package entities.classes;

public non-sealed class Knight extends Classes {
    //Habilidades
    //• Golpe Resoluto – causa 18 dano, cooldown 2 turnos
    //• Defesa Firme – +6 escudo por 1 turno
    //• Investida – causa 10 dano e dá +1 move speed no turno

    private double vida = 55;
    private double dano = 12;
    private double escudo = 4;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public Knight(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super(vida, dano, escudo, attackSpeed, moveSpeed);
    }
}
