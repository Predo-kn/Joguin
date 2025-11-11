import entities.classes.Guerreiro;
import entities.inimigos.Lobo;

public class Main {
    public static void main(String[] args) {
        Guerreiro guerreiro = new Guerreiro(25, 10, 5, 15, 15);
        Lobo lobo = new Lobo(25, 10, 5, 10, 10);

        while(guerreiro.getVida() > 0 && lobo.getVida() > 0){
            guerreiro.atacar(lobo);
            lobo.atacar(guerreiro);
        }
    }
}