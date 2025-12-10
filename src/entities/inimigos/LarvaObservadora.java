package entities.inimigos;

public non-sealed class LarvaObservadora extends Inimigo {

    private double vida = 35;
    private double dano = 6;
    private double escudo = 3;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public LarvaObservadora() {
        super(35, 6, 3, 2, 2);
        carregarAtributosDoArquivo("LarvaObservadora");
    }

    public LarvaObservadora(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
