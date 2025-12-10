package entities.inimigos;

public non-sealed class Carnical extends Inimigo {

    private double vida = 40;
    private double dano = 7;
    private double escudo = 2;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public Carnical() {
        super(40, 7, 2, 2, 2);
        carregarAtributosDoArquivo("Carnical");
    }

    public Carnical(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
