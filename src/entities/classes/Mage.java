package entities.classes;

public non-sealed class Mage extends Classes {
    //Habilidades
    //• Raio Arcano – 22 dano
    //• Barreira Etérea – +4 escudo por 1 turno
    //• Sifão Mágico – causa 10 dano e recupera 5 vida

    private double vida = 40;
    private double dano = 16;
    private double escudo = 0;
    private double atackSpeed = 2;
    private double moveSpeed = 2;

    public Mage(double vida, double dano, double escudo, double attackSpeed, double moveSpeed) {
        super(vida, dano, escudo, attackSpeed, moveSpeed);
    }
}
