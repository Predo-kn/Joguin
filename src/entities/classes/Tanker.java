package entities.classes;

public non-sealed class Tanker extends Classes {
    private double vida = 75;
    private double dano = 8;
    private double escudo = 6;
    private double atackSpeed = 1;
    private double moveSpeed = 1;

    public Tanker(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super(vida, dano, escudo, attackSpeed, moveSpeed);
    }
}
