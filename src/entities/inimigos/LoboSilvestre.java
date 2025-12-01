package entities.inimigos;

import entities.classes.Classes;
import entities.itens.Item;
import entities.itens.PresaDoLobo;
import entities.itens.Raridade;

public non-sealed class LoboSilvestre extends Inimigo {

    private double vida = 25;
    private double dano = 5;
    private double escudo = 1;
    private double atackSpeed = 3;
    private double moveSpeed = 4;
    private Item[] item;

    public LoboSilvestre(double vida, double dano, double escudo, double atackSpeed, double moveSpeed) {
        super(vida, dano, escudo, atackSpeed, moveSpeed);
        item = new Item[2];
    }

    public void addItens(Item[] itens){
        PresaDoLobo  pdl = new PresaDoLobo();
        itens[0] = pdl;

    }
}
