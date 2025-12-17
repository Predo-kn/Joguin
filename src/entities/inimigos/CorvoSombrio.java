package entities.inimigos;

public non-sealed class CorvoSombrio extends Inimigo {

    private double vida = 22;
    private double dano = 4;
    private double escudo = 0;
    private double atackSpeed = 4;
    private double moveSpeed = 4;

    public CorvoSombrio() {
        super(22, 4, 0, 4, 5);
        carregarAtributosDoArquivo("CorvoSombrio");
    }

    public CorvoSombrio(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
