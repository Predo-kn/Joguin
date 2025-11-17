import entities.classes.Knight;

public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight(25, 10, 5, 10, 10);
        Wolf wolf = new Wolf(25, 10, 5, 10, 10);

        while(knight.getVida() > 0 && wolf.getVida() > 0){
            knight.atacar(wolf);
            wolf.atacar(knight);
        }
    }
}