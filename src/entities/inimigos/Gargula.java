package entities.inimigos;

import java.io.File;

public non-sealed class Gargula extends Inimigo {
    public String nomeData = "Gargula.txt";
    public Gargula(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
    }
}
