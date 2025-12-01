package entities.inimigos;

public non-sealed class EcoAbismo extends Inimigo {

    private double vida = 50;
    private double dano = 8;
    private double escudo = 3;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public EcoAbismo(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
