package entities.inimigos;

public non-sealed class LoboSilvestre extends Inimigo {

    private double vida = 25;
    private double dano = 5;
    private double escudo = 1;
    private double atackSpeed = 3;
    private double moveSpeed = 4;

    public LoboSilvestre() {
        super(25, 5, 1, 3, 4);
        carregarAtributosDoArquivo("LoboSilvestre");
    }

    public LoboSilvestre(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
