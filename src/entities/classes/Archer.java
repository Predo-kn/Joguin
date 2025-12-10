package entities.classes;

public non-sealed class Archer extends Classes {
    private double vida = 45;
    private double dano = 10;
    private double escudo = 2;
    private double atackSpeed = 3;
    private double moveSpeed = 4;

    public Archer(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super(vida, dano, escudo, attackSpeed, moveSpeed);
    }


}
