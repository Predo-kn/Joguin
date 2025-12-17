package entities.classes;

public non-sealed class Mage extends Classes {
    private double vida = 40;
    private double dano = 16;
    private double escudo = 0;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public Mage(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super("Knight", vida, dano, escudo, attackSpeed, moveSpeed);
    }

    public Mage() {
        super("Mage", 40, 16, 0, 2, 2);
        carregarAtributosDoArquivo("Mage");
    }
}
