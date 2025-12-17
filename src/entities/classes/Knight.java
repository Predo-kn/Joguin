package entities.classes;

public non-sealed class Knight extends Classes {
    private double vida = 55;
    private double dano = 12;
    private double escudo = 4;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public Knight(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super("Knight", vida, dano, escudo, attackSpeed, moveSpeed);
    }

    public Knight() {
        super("Knight", 55, 12, 4, 2, 2);
        carregarAtributosDoArquivo("Knight");
    }
}
