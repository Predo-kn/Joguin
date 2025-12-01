package entities.inimigos;

public non-sealed class SemRosto extends Inimigo {

    private double vida = 70;
    private double dano = 10;
    private double escudo = 4;
    private double atackSpeed = 3;
    private double moveSpeed = 3;

    public SemRosto(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
